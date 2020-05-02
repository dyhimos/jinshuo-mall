package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.utils.*;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.shopMessageConfig.ShopMessageConfig;
import com.jinshuo.mall.service.user.application.cmd.ChangeMobileCmd;
import com.jinshuo.mall.service.user.application.cmd.CompletionMemberCmd;
import com.jinshuo.mall.service.user.application.cmd.UpMemberPhoneCmd;
import com.jinshuo.mall.service.user.application.dto.MsgDto;
import com.jinshuo.mall.service.user.mybatis.MemberRepo;
import com.jinshuo.mall.service.user.mybatis.UserAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author dongyh
 * @Classname MemberCmdService
 * @Description 会员
 * @Date 2019/7/8 15:37
 * @Created by dongyh
 */
@Slf4j
@Service
public class MemberCmdService {

    @Autowired
    private MemberRepo memberRepo;

/*    @Autowired
    private WxConfigRepo wxConfigRepo;*/

    @Autowired
    private UserAccountRepo userAccountRepo;

/*    @Autowired
    private DistributionServiceResponse distributionServiceResponse;*/

    /*@Autowired 发送短信
    private SendMsgMQ sendMsgMQ;*/

    @Autowired
    private ShopMessageConfigService shopMessageConfigService;


    /**
     * 完善客户信息
     *
     * @param cmd
     * @return
     */
    public int cmpletionUserInfo(CompletionMemberCmd cmd) {
        log.info(" -- 完善客户信息,输入参数：{}", cmd);
        String msg = null;
        if (null != cmd.getIdCard()) {
            msg = IdCardUtil.IdentityCardVerification(cmd.getIdCard());
            if (!"correct".equals(msg)) {
                throw new UcBizException(msg);
            }
        }
        Member member = memberRepo.queryByUserId(UserIdUtils.getUserId());
        if (null == member) {
            throw new UcBizException(UcReturnCode.UC200002.getMsg());
        }
        member.comMember(cmd.getSurname(), cmd.getIdCard(), cmd.getNickname(), cmd.getAvatar(), cmd.getPayNo(), cmd.getWxNo());
        memberRepo.comInfo(member);
        cmd.setUserAccountId(member.getUserAccountId().getId());
        /*try {
            distributionServiceResponse.comSaleman(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return 1;
    }

    @Autowired
    public HttpServletRequest request;

    /**
     * 获取图片插件的值
     */
    public String geetest() {
        GeetestLibUtils gtSdk = new GeetestLibUtils(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());
        Long userId = UserIdUtils.getUserId();
        //Long userId = 123L;
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        //网站用户id
        param.put("user_id", userId.toString());
        //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("client_type", "h5");
        //传输用户请求验证时所携带的IP
        param.put("ip_address", HttpUtils.getIpAddress());

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        RedisUtil.setex(gtSdk.gtServerStatusSessionKey + userId.toString(), String.valueOf(gtServerStatus), 3 * 60);

        String resStr = gtSdk.getResponseStr();
        return resStr;
    }


    /**
     * 二次验证插件
     *
     * @param changeMobileCmd
     * @return
     * @throws UnsupportedEncodingException
     */
    public Boolean verify(ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        GeetestLibUtils gtSdk = new GeetestLibUtils(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());
        String challenge = changeMobileCmd.getGee().getGeetest_challenge();
        String validate = changeMobileCmd.getGee().getGeetest_validate();
        String seccode = changeMobileCmd.getGee().getGeetest_seccode();
        Long userId = UserIdUtils.getUserId();
        //Long userId = 123L;
        //从session中获取gt-server状态
        int gt_server_status_code = Integer.valueOf(RedisUtil.getStr(gtSdk.gtServerStatusSessionKey + userId.toString()));
        //从session中获取userid

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        //网站用户id
        param.put("user_id", userId.toString());
        //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("client_type", "h5");
        //传输用户请求验证时所携带的IP
        param.put("ip_address", HttpUtils.getIpAddress());

        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        JSONObject data = new JSONObject();
        if (gtResult == 1) {
            // 验证成功
            return true;
        } else {
            // 验证失败
           /* try {
                data.put("status", "fail");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            return false;
        }
    }

    /**
     * 发送短信
     *
     * @param changeMobileCmd
     * @throws UnsupportedEncodingException
     */
    public void sendMsg(ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
       /* Boolean flag = verify(changeMobileCmd);
        if (flag) {
            //发送短信
            Long userId = UserIdUtils.getUserId();
            String code = RedisUtil.getStr(String.valueOf(userId) + "msgCode");
            if (StringUtils.isBlank(code)) {
                code = RandomUtil.getNumRandom(6);
                RedisUtil.setex(String.valueOf(userId) + "msgCode", code, 10 * 60);
            }
            log.info("获取的随机字符串为：{}", code);
            String content = "您的验证码是" + code + "，十分钟内有效，请勿泄露于他人，以免账号被盗！";
            Map<String, String> map = new HashMap<>();
            map.put("mobile", changeMobileCmd.getMobile());
            map.put("content", content);
            //发送短息
            sendMsgMQ.sendMsg(map);
            RedisUtil.setex(String.valueOf(userId) + "msgPhone", changeMobileCmd.getMobile(), 10 * 60);
        } else {
            throw new UcBizException(UcReturnCode.UC200020.getMsg(), UcReturnCode.UC200020.getCode());
        }*/
    }


    /**
     * 发送短信(未登陆)
     *
     * @param changeMobileCmd
     * @throws UnsupportedEncodingException
     */
    public void sendMsgOutLogin(ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        log.info("  -- 发送短信(未登陆)，输入参数{}", changeMobileCmd);
        //Boolean flag = verify(changeMobileCmd); 校验图片
        Boolean flag = true;
        if (flag) {
            //发送短信
            String code = RedisUtil.getStr(String.valueOf(changeMobileCmd.getMobile()) + "msgCode");
            if (StringUtils.isBlank(code)) {
                code = RandomUtil.getNumRandom(6);
                RedisUtil.setex(String.valueOf(changeMobileCmd.getMobile()) + "msgCode", code, 10 * 60);
            }
            //log.info("获取的随机字符串为：{}", code);
            String content = "尊敬的客户：您本次操作的验证码为：" + code;
            MsgDto msgDto = MsgDto.builder()
                    .mobile(changeMobileCmd.getMobile())
                    .content(content)
                    .shopId(10066L)
                    .build();
            setSendMsg(msgDto);
        } else {
            throw new UcBizException(UcReturnCode.UC200020.getMsg(), UcReturnCode.UC200020.getCode());
        }
    }

    /**
     * 给安全手机发送短信(未登陆)
     *
     * @param changeMobileCmd
     * @throws UnsupportedEncodingException
     */
    public void sendMsgSafetyPhone(ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        Member member = memberRepo.queryByUserId(UserIdUtils.getUserId());
        changeMobileCmd.setMobile(member.getPhone());
        sendMsgOutLogin(changeMobileCmd);
    }

    /**
     * 修改手机号码
     *
     * @param upMemberPhoneCmd
     * @return
     */
    public int changePhone(UpMemberPhoneCmd upMemberPhoneCmd) {
        Long userId = UserIdUtils.getUserId();
        String code = RedisUtil.getStr(String.valueOf(userId) + "msgCode");
        String phone = RedisUtil.getStr(String.valueOf(userId) + "msgPhone");
        if (StringUtils.isNotBlank(code)) {
            Boolean flagCode = false;
            Boolean flagPhone = false;
            if (code.equals(upMemberPhoneCmd.getCode()) && phone.equals(upMemberPhoneCmd.getPhone())) {
                flagCode = true;
            } else {
                throw new UcBizException(UcReturnCode.UC200017.getMsg(), UcReturnCode.UC200017.getCode());
            }

            if (phone.equals(upMemberPhoneCmd.getPhone())) {
                flagPhone = true;
            } else {
                throw new UcBizException(UcReturnCode.UC200018.getMsg(), UcReturnCode.UC200018.getCode());
            }

            if (flagCode && flagPhone) {
                Member member = memberRepo.queryByUserId(userId);
                member.setPhone(upMemberPhoneCmd.getPhone());
                return memberRepo.updatePhone(member);
            }
        } else {
            throw new UcBizException(UcReturnCode.UC200019.getMsg(), UcReturnCode.UC200019.getCode());
        }
        return 0;
    }


    /**
     * 添加客户
     */
    public void saveCustomerInfo(Member member) {
        memberRepo.save(member);
    }


    /**
     * 发送短信
     *
     * @param dto
     */
    public void setSendMsg(MsgDto dto) throws UcBizException {
        ShopMessageConfig shopMessageConfig = shopMessageConfigService.queryByShopId(dto.getShopId());
        dto.setUsername(shopMessageConfig.getUsername());
        dto.setPassword(shopMessageConfig.getPassword());
        dto.setSignName(shopMessageConfig.getSignName());
        try {
            //log.info(" -- JSONObject.toJSONString(dto)" + JSONObject.toJSONString(dto));
            SendMsgUtils.sendMsg(JSONObject.toJSONString(dto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UcBizException(UcReturnCode.UC200034.getMsg(), UcReturnCode.UC200034.getCode());
        }
    }
}
