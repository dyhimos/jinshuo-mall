package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSON;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.UserAuthDto;
import com.jinshuo.core.utils.*;
import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.member.MemberId;
import com.jinshuo.mall.domain.user.model.merchant.Merchant;
import com.jinshuo.mall.domain.user.model.scoreAccount.MemberScoreAccount;
import com.jinshuo.mall.domain.user.model.supplier.Supplier;
import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountStatusEnums;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatformId;
import com.jinshuo.mall.service.finance.mybatis.FinanceAccountCashRepo;
import com.jinshuo.mall.service.user.application.assermbler.UserAccountAssembler;
import com.jinshuo.mall.service.user.application.assermbler.UserInfoAssembler;
import com.jinshuo.mall.service.user.application.cmd.*;
import com.jinshuo.mall.service.user.application.dto.*;
import com.jinshuo.mall.service.user.mybatis.*;
import com.jinshuo.mall.service.user.service.query.MerchantManagerQueryService;
import com.jinshuo.mall.service.user.service.query.ShopQueryService;
import com.jinshuo.mall.service.user.service.query.SupplierManagerQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author dongyh
 * @Classname GoodsOrderCmdService
 * @Description 产品订单
 * @Date 2019/7/8 15:37
 * @Created by dongyh
 */
@Slf4j
@Service
public class UserAccountCmdService {

    @Autowired
    private UserAccountPlatformRepo userAccountPlatformRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MemberCmdService memberCmdService;

    @Autowired
    private MerchantRepo merchantRepo;

    @Autowired
    private ShopQueryService shopQueryService;

    @Autowired
    private SupplierManagerRepo supplierManagerRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private SupplierManagerComService supplierManagerComService;

    @Autowired
    private MerchantManagerQueryService merchantManagerQueryService;

    @Autowired
    private SupplierManagerQueryService supplierManagerQueryService;

    @Autowired(required = false)
    private ClientDetailsService clientDetailsService;

    //@Autowired(required = false)
    //private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private LoginReportComService loginReportComService;

    /**
     * 资金账户
     */
    @Autowired
    private FinanceAccountCashRepo financeAccountCashRepo;

    /**
     * 资金账户
     */
    @Autowired
    private ScoreAccountRepo scoreAccountRepo;

    @Autowired
    private RoleRepo roleRepo;

    /**
     * app登录
     *
     * @param appLoginCmd
     * @return
     */
    public OAuth2AccessToken appLogin(AppLoginCmd appLoginCmd) throws IOException {
        String username = StringUtils.trim(appLoginCmd.getUsername());
        String password = StringUtils.trim(appLoginCmd.getPassword());
        //检查用户名和密码是否正确
        UserAccount userAccount = UserAccount.checkLoginInfoLogin(username, password, 1);

        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //封装自定义参数
        UserAuthDto userAuthDto = null;
        if (userAccount != null) {
            userAuthDto = new UserAuthDto(userAccount.getUserAccountId().getId().toString(), userAccount.getUsername(),
                    userAccount.getNickname(), userAccount.getPassword(), userAccount.getShopId(), true);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAuthDto, null, userAuthDto.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String header = request.getHeader("Authorization");
        String header = "Basic eW0tbWFsbC13b29sOnlpbWFpQDEyMzQ1Ng==";
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        String[] tokens = extractAndDecodeHeader(header, null);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        //创建token
        //OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        return null;
    }


    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    /**
     * 登录
     *
     * @param wxMiniCancellationLoginCmd
     */
    public MerchantLoginInfoDto miniCancellationLogin(WxMiniCancellationLoginCmd wxMiniCancellationLoginCmd) {
        /*//检查用户名和密码是否正确
        UserAccount userAccount = UserAccount.checkLoginInfo(wxMiniCancellationLoginCmd.getUsername(), wxMiniCancellationLoginCmd.getPassword());
        SupplierManager supplierManager = supplierManagerQueryService.getByUserId(userAccount.getUserAccountId().getId());
        if (null == supplierManager || 1 == supplierManager.getSupplierManagerStatus()) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
        }
        JWT jwt = authServiceClient.getToken("Basic eW0tY2VudGVyLXVzZXI6MTIzNDU2", "password",
                wxMiniCancellationLoginCmd.getUsername() + ",pc", wxMiniCancellationLoginCmd.getPassword());
        if (jwt == null) {
            throw new UcBizException(UcReturnCode.UC200011.getMsg(), UcReturnCode.UC200011.getCode());
        }
        //获取基本信息
        MerchantLoginInfoDto dto = MerchantLoginInfoDto.builder()
                .id(userAccount.getUserAccountId().getId())
                .username(userAccount.getUsername())
                .token(jwt.getAccess_token())
                .build();
        RedisUtil.setex(userAccount.getUserAccountId().getId().toString(), JSONObject.toJSONString(supplierManagerComService.getInfoByAccountId(userAccount.getUserAccountId().getId())), 24 * 60 * 60);
        return dto;*/
        return null;
    }


    /**
     * 生成邀请码
     *
     * @param
     * @return
     */
    private String randomInviteCode() {
        String inviteCode = RandomUtil.getNumRandom(8);
        UserAccount ua = userAccountRepo.findByInvaildCode(inviteCode);
        //如果邀请码存在，则重新生成
        if (ua != null) {
            randomInviteCode();
        }
        return inviteCode;
    }


    /**
     * 生成随机用户名
     *
     * @param
     * @return
     */
    public String randomUserName() {
        String userName = RandomUtil.getNumRandom(8);
        UserAccount ua = userAccountRepo.findByUserName(userName);
        //如果邀请码存在，则重新生成
        if (ua != null) {
            randomUserName();
        }
        return userName;
    }


    /**
     * 构建用户账户信息
     *
     * @param wxMpUser
     * @param userAccountId
     * @return
     */
    public UserAccount getUserAccount(WxMpUser wxMpUser, Long userAccountId, Long shopId) throws UnsupportedEncodingException {
        UserAccount userAccount;
        userAccount = new UserAccount().save(
                new UserAccountId(userAccountId)
                //默认用户名（随机生成）
                , randomUserName()
                //URLEncoder.encode(wxMpUser.getNickname(), "utf-8"),
                , BPwdEncoderUtils.BCryptPassword("123456")
                , 1
                , ""
                , ""
                , ""
                , randomInviteCode()
                , shopId == null ? 10088 : shopId
                , HttpUtils.getIpAddress()
                , 1,
                UserAccountStatusEnums.ACCOUNT_ENABLE
        );
        return userAccount;
    }


    /**
     * 构建会员信息，会员积分信息
     *
     * @param wxMpUser
     * @param userAccountId
     * @param pMember
     * @param pid
     * @return
     */
    public Member getMember(WxMpUser wxMpUser, Long userAccountId, Member pMember, Long pid, Long shopId) throws UnsupportedEncodingException {
        Date subscribeTime = null;
        if (wxMpUser.getSubscribeTime() == null) {
            subscribeTime = new Date();
        } else {
            subscribeTime = new Date(wxMpUser.getSubscribeTime() * 1000);
        }

        MemberId memberId = memberRepo.nextId();
        //会员积分账户信息
        MemberScoreAccount memberScoreAccount = MemberScoreAccount.builder()
                .memberScoreAccountId(scoreAccountRepo.nextId())
                .memberId(memberId)
                .expendScore(0)
                .totalScore(0)
                .useableScore(0)
                .build();

        Member member;
        member = Member.builder()
                .memberId(memberId)
                .shopId(shopId)
                .openid(wxMpUser.getOpenid())
                .subscribeTime(subscribeTime)
                //判断是否关注
                .isFans(wxMpUser.getSubscribe() == null ? 0 : 1)
                //年龄
                //.age()
                //.name(URLEncoder.encode(wxMpUser.getNickname(), "utf-8"))
                //微信昵称中带有Emiji处理
                .nickname("")
                .sex(SexEnums.getEnumByCode(Integer.valueOf(wxMpUser.getSex())))
                .country(wxMpUser.getCountry())
                .city(wxMpUser.getCity())
                .province(wxMpUser.getProvince())
                .avatar(wxMpUser.getHeadImgUrl())
                //默认消费金额0
                .consumeAmount(new BigDecimal(0))
                //默认订单数0
                .consumeOrder(0)
                .type(3)
                .sourceCanal(1)
                .isDis(2)
                .pid(pid)
                .path(userAccountId.toString())
                .parentMember(pMember)
                .memberScoreAccount(memberScoreAccount)
                .userAccountId(new UserAccountId(userAccountId))
                .build();
        member.preInsert();
        return member;
    }


    /**
     * 构建微信用户信息
     *
     * @param wxMpUser
     * @param userAccount
     * @param member
     * @return
     */
    public UserAccountPlatform getUserAccountPlatform(WxMpUser wxMpUser, UserAccount userAccount, Member member, Long shopId) throws UnsupportedEncodingException {
        return UserAccountPlatform.builder()
                .userAccountPlatformId(new UserAccountPlatformId(CommonSelfIdGenerator.generateId()))
                .userAccountId(userAccount.getUserAccountId())
                .userAccount(userAccount)
                .member(member)
                .openid(wxMpUser.getOpenid())
                .unionid(wxMpUser.getUnionId())
                .avatar(wxMpUser.getHeadImgUrl())
                .nickname("")
                //.nickname(URLEncoder.encode(wxMpUser.getNickname(), "utf-8"))
                .sex(SexEnums.getEnumByCode(Integer.valueOf(wxMpUser.getSex())))
                .shopId(shopId == null ? 10088 : shopId)
                .type("1")
                .build();
    }

    /**
     * 登录信息逻辑处理
     *
     * @param shopId       店铺
     * @param fromUserName 用户openid
     * @param inviteCode   邀请码
     * @param inviteCode   调用渠道 1：公众平台网页授权  2：公众平台关注/扫码事件事件
     * @throws Exception
     */
    public void loginAction(Long shopId, String fromUserName, String inviteCode, WxMpUser wxMpUser, String channel) throws Exception {
        String openid = fromUserName;
        //判断是此用户是否在数据库中存在
        Boolean flag = UserAccountPlatform.chekIsExit(openid, shopId);

        if (flag) {
            //判断是unionId否存在（微信多平台登录账号统一使用unionId判断）
            Boolean flagUnionId = UserAccountPlatform.chekIsExitUnionId(wxMpUser.getUnionId(), shopId);
            Long userAccountId = null;

            UserAccount userAccount = null;

            Member member = null;

            //更新为分销用户的对象
            Member pMember = null;

            //父节点
            Long pid = null;

            if (flagUnionId) {
                //用户信息
                userAccountId = CommonSelfIdGenerator.generateId();

                //用户账户信息
                userAccount = getUserAccount(wxMpUser, userAccountId, shopId);
                //会员信息
                member = getMember(wxMpUser, userAccountId, pMember, pid, shopId);

                //判断是否有邀请码
                if (StringUtils.isNotBlank(inviteCode)) {
                    UserAccount parentUser = userAccountRepo.findByInvaildCode(inviteCode);
                    //如果邀请码有效
                    if (parentUser != null) {
                        pid = parentUser.getUserAccountId().getId();
                        //设置父id
                        member.setPid(pid);
                        //更改父路径
                        member.setPath(pid.toString().concat(",").concat(userAccountId.toString()));
                    } else {
                        log.error("查询inviteCode:{}的用户不存在", inviteCode);
                        throw new UcBizException(UcReturnCode.UC200004.getMsg(), UcReturnCode.UC200004.getCode());
                    }
                }
            } else {
                userAccountId = userAccountPlatformRepo.findByUnionId(wxMpUser.getUnionId(), shopId).get(0).getUserAccountId().getId();
                userAccount = userAccountRepo.findById(new UserAccountId(userAccountId));
            }

            //微信用户信息
            UserAccountPlatform userAccountPlatform = getUserAccountPlatform(wxMpUser, userAccount, member, shopId);
            userAccountPlatformRepo.save(userAccountPlatform);

        }
        //判断微信信息更改更新
        else {
            UserAccountPlatform userAccountPlatform = userAccountPlatformRepo.findByOpenid(openid, shopId);
            userAccountPlatform.setAvatar(wxMpUser.getHeadImgUrl());
            userAccountPlatform.setNickname("");
            userAccountPlatform.getMember().setNickname("");
            userAccountPlatform.getMember().setAvatar(wxMpUser.getHeadImgUrl());
            //如果是关注/扫码进入，更新用户的状态为关注状态
            if ("2".equals(channel)) {
                userAccountPlatform.getMember().setIsFans(1);
            }
            userAccountPlatformRepo.update(userAccountPlatform);
        }
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoDto getUserInfo() throws UnsupportedEncodingException {

        //UserAuthDto userAuthDto = UserIdUtils.getUser();
        Long userId = UserIdUtils.getUserId();
        log.info("当前用户ID为：{}" + userId);
        UserAccountPlatform userAccountPlatform = userAccountPlatformRepo.findByUserId(new UserAccountId(userId));

        UserInfoDto userInfoDto = null;
        //获取用户信息失败
        if (userAccountPlatform == null) {
            //throw new UcBizException(UcReturnCode.UC200002.getMsg(),UcReturnCode.UC200002.getCode());
            Member member = memberRepo.queryByUserId(userId);
            userInfoDto = UserInfoAssembler.assembleMemberInfoDto(member);
            userInfoDto.setNickname(UserIdUtils.getUser().getNickname());
        }

        loginLog(userId);
        return userInfoDto;
    }


    /**
     * 根据用户Id查询用户信息
     *
     * @param userId
     * @return
     */
    public UserAccountPlatformDto getUserInfoByUserId(Long userId) {
        UserAccountPlatform userAccountPlatform = userAccountPlatformRepo.findByUserId(new UserAccountId(userId));
        return UserAccountAssembler.assembleUserAccount(userAccountPlatform);
    }


    /**
     * 封装认证数据对象
     *
     * @param username 用户名
     */
    public UserAuthDto getAuth(String username) {
        UserAccount userAccount = userAccountRepo.findByUserName(username);
        UserAuthDto userAuthDto = null;
        if (userAccount != null) {
            userAuthDto = new UserAuthDto(userAccount.getUserAccountId().getId().toString(), userAccount.getUsername(),
                    userAccount.getNickname(), userAccount.getPassword(), userAccount.getShopId(), true);
        }
        return userAuthDto;
    }


    /**
     * 封装PC登录认证数据对象
     *
     * @param username 用户名
     */
    public UserAuthDto getPcAuth(String username) {
        UserAccount userAccount = userAccountRepo.findByUserName(username);
        //Merchant merchant = merchantRepo.findByUserId(userAccount.getUserAccountId().getId());
        Merchant merchant = merchantManagerQueryService.getMerchantByUserId(userAccount.getUserAccountId().getId());
        UserAuthDto userAuthDto = null;
        if (userAccount != null) {
            if (null != merchant) {
                userAuthDto = new UserAuthDto(userAccount.getUserAccountId().getId().toString(), userAccount.getUsername(),
                        userAccount.getPassword(), merchant.getMerchantId().getId(), true);
            } else {
                userAuthDto = new UserAuthDto(userAccount.getUserAccountId().getId().toString(), userAccount.getUsername(),
                        userAccount.getPassword(), null, true);
            }
        }
        return userAuthDto;
    }


    /**
     * 根据id获取供应商信息
     *
     * @param userId 供应商信息
     */
    public SupplierDto getSupplierInfo(Long userId) {
        //获取用户信息
        UserAccount userAccount = userAccountRepo.findById(new UserAccountId(userId));
        if (userAccount == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
        }
        if (!UserAccount.supplierType.equals(userAccount.getType())) {
            throw new UcBizException(UcReturnCode.UC200002.getMsg(), UcReturnCode.UC200002.getCode());
        }

        //获取供应商下面核销人员信息
        SupplierManager supplierManager = supplierManagerRepo.queryByUserAccountId(userId);
        //查询供应商信息
        Supplier supplier = supplierRepo.findById(supplierManager.getSupplierId());
        SupplierDto supplierDto = new SupplierDto();
        BeanUtils.copyProperties(supplier, supplierDto);
        supplierDto.setId(supplier.getSupplierId().getId().toString());
        return supplierDto;
    }

    /**
     * 后台修改密码
     *
     * @param cmd
     */
    public void updatePassword(LoginPasswordCmd cmd) {
        UserAuthDto dto = UserIdUtils.getUser();
        //检查用户名和密码是否正确
        UserAccount userAccount = UserAccount.checkLoginInfoLogin(dto.getUsername(), cmd.getPassword(), 5);
        userAccount.setPassword(BPwdEncoderUtils.BCryptPassword(cmd.getNewPassword()));
        userAccountRepo.updatePassword(userAccount);
    }

    /**
     * 重置密码
     *
     * @param cmd
     */
    public void resetPassword(LoginPasswordCmd cmd) {
        String code = RedisUtil.getStr(String.valueOf(cmd.getPhone()) + "msgCode");
        if (null == cmd.getPhone() || null == cmd.getCode() || null == code || !code.equals(cmd.getCode())) {
            throw new UcBizException(UcReturnCode.UC200019.getMsg());
        }
        //检查用户名和密码是否正确
        UserAccount userAccount = userAccountRepo.findByPhoneAndType(cmd.getPhone(), 1);
        if (userAccount == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        userAccount.setPassword(BPwdEncoderUtils.BCryptPassword(cmd.getNewPassword()));
        userAccountRepo.updatePassword(userAccount);
    }


    /**
     * 设置交易密码
     *
     * @param cmd
     */
    public void setPayPassword(PayPasswordCmd cmd) {
        Long userId = UserIdUtils.getUserId();
        if (userId == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        Member member = memberRepo.queryByUserId(userId);
        if (member == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        member.setPayPassword(BPwdEncoderUtils.BCryptPassword(cmd.getPayPassword()));
        memberRepo.updatePayPassword(member);
    }

    /**
     * 修改支付密码
     *
     * @param cmd
     */
    public void modifyPayPassword(PayPasswordCmd cmd) {
        Long userId = UserIdUtils.getUserId();
        if (userId == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        Member member = memberRepo.queryByUserId(userId);
        if (member == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        //判断交易密码是否正确
        Boolean flag = BPwdEncoderUtils.matches(cmd.getOldPayPassword(), member.getPayPassword());
        //判断之前的交易密码是否正确
        if (!flag) {
            throw new UcBizException(UcReturnCode.UC200038.getMsg(), UcReturnCode.UC200038.getCode());
        }
        member.setPayPassword(BPwdEncoderUtils.BCryptPassword(cmd.getPayPassword()));
        memberRepo.updatePayPassword(member);
    }


    /**
     * 重置交易密码
     *
     * @param cmd
     */
    public void resetPayPassword(PayPasswordCmd cmd) {
        Long userId = UserIdUtils.getUserId();
        if (userId == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        Member member = memberRepo.queryByUserId(userId);
        if (member == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        log.info("用户id为：{}", userId);
        log.info("接收的手机号码为：{}", cmd.getPhone());
        log.info("数据库中获取的手机号码为：{}", member.getPhone());
        //判断预留手机号码是否正确
        if (!member.getPhone().equals(cmd.getPhone())) {
            throw new UcBizException(UcReturnCode.UC200039.getMsg(), UcReturnCode.UC200039.getCode());
        }

        String code = RedisUtil.getStr(String.valueOf(cmd.getPhone()) + "msgCode");
        if (!code.equals(cmd.getCode())) {
            throw new UcBizException(UcReturnCode.UC200019.getMsg());
        }
       /* Long userId = 190885212542992384L;
        Member member = memberRepo.queryByUserId(userId);*/
        member.setPayPassword(BPwdEncoderUtils.BCryptPassword(cmd.getPayPassword()));
        memberRepo.updatePayPassword(member);
    }


    public void saveUser(UserAccount userAccount) {
        userAccountRepo.save(userAccount);
    }

    public void updateUser(UserAccount userAccount) {
        userAccountRepo.update(userAccount);
    }

    /**
     * 记录登录信息
     */
    public void loginLog(Long userId) {
        log.info(" -- 记录登录信息{}", userId);
        if (null == userId) {
            return;
        }
        UserAccount userAccount = userAccountRepo.findById(new UserAccountId(userId));
        if (null == userAccount) {
            return;
        }
        userAccount = userAccount.loginLog();
        try {
            loginReportComService.recordingLogin(userAccount);
            userAccount = userAccount.loginLog();
            userAccountRepo.updateLoginLog(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用户注册
     *
     * @param cmd
     * @return
     */
    public OAuth2AccessToken registered(RegisteredCmd cmd) throws IOException {
        log.info(" -- 用户注册,输入参数，{}", cmd);
        String code = RedisUtil.getStr(String.valueOf(cmd.getPhone()) + "msgCode");
        if (null == cmd.getPhone() || null == cmd.getVerificationCode() || null == code || !code.equals(cmd.getVerificationCode())) {
            throw new UcBizException(UcReturnCode.UC200019.getMsg(), UcReturnCode.UC200035.getCode());
        }
        List<UserAccount> userAccountList = userAccountRepo.findByUserNameAndType(cmd.getPhone(), 1);
        if (userAccountList.size() > 0) {
            throw new UcBizException(UcReturnCode.UC200035.getMsg(), UcReturnCode.UC200035.getCode());
        }
        UserAccount userAccount = UserAccount.builder()
                .userAccountId(new UserAccountId(CommonSelfIdGenerator.generateId()))
                .shopId(cmd.getShopId())
                .nickname(cmd.getUsername())
                .username(cmd.getPhone())
                .password(BPwdEncoderUtils.BCryptPassword(cmd.getPassword()))
                .type(1)
                .phone(cmd.getPhone())
                .userStatus(UserAccountStatusEnums.ACCOUNT_ENABLE)
                .build();
        userAccountRepo.save(userAccount);
        Member member = Member.builder()
                .shopId(cmd.getShopId())
                .phone(cmd.getPhone())
                .userAccountId(userAccount.getUserAccountId())
                .memberId(new MemberId(CommonSelfIdGenerator.generateId()))
                .nickname(cmd.getUsername())
                .build();
        member.init();
        memberCmdService.saveCustomerInfo(member);

        //现金账户初始化
        FinanceAccountCash financeAccountCash = FinanceAccountCash.builder()
                .shopId(cmd.getShopId())
                .memId(userAccount.getUserAccountId().getId())
                .build();
        financeAccountCash.init();
        financeAccountCashRepo.create(financeAccountCash);

        //积分账户
        MemberScoreAccount memberScoreAccount = MemberScoreAccount.builder()
                .memberScoreAccountId(scoreAccountRepo.nextId())
                .memberId(member.getMemberId())
                .totalScore(0)
                .expendScore(0)
                .useableScore(0)
                .build();
        scoreAccountRepo.save(memberScoreAccount);

        AppLoginCmd cmd1 = new AppLoginCmd();
        cmd1.setPassword(cmd.getPassword());
        cmd1.setUsername(cmd.getPhone());
        return appLogin(cmd1);
    }


    /**
     * 后台登录
     *
     * @param appLoginCmd
     * @return
     */
    public MerchantLoginInfoDto login(MerchantLoginCmd appLoginCmd) throws IOException {
        String username = StringUtils.trim(appLoginCmd.getUsername());
        String password = StringUtils.trim(appLoginCmd.getPassword());
        //检查用户名和密码是否正确 管理后台用户类型为5
        UserAccount userAccount = UserAccount.checkLoginInfoLogin(username, password, 5);

        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //封装自定义参数
        UserAuthDto userAuthDto = null;
        if (userAccount != null) {
            userAuthDto = new UserAuthDto(userAccount.getUserAccountId().getId().toString(), userAccount.getUsername(),
                    userAccount.getNickname(), userAccount.getPassword(), userAccount.getShopId(), true);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAuthDto, null, userAuthDto.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String header = request.getHeader("Authorization");
        String header = "Basic eW0tbWFsbC13b29sOnlpbWFpQDEyMzQ1Ng==";
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        String[] tokens = extractAndDecodeHeader(header, null);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        //创建token
        //OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        MerchantLoginInfoDto dto = MerchantLoginInfoDto.builder()
                .shops(shopQueryService.getList(userAccount.getMerchantId()))
                .username(userAccount.getUsername())
                .token(null)
                .avatar("/10088/1/129603212297633792/co/39d0b82f-0200-4bbc-9106-c811858bb08d.png")
                .build();
        return dto;
    }


    /**
     * 保存管理员账户
     */
    public void saveManagerUser(UserManagerAccountCmd cmd) {
        Long shopId = 10066L;
        Long userId = null;
        String password = null;
        List<Long> commitRoleIds = cmd.getRoleIds();
        List<Long> commitMenuIds = cmd.getMenuIds();
        if (cmd.getId() != null) {
            userId = cmd.getId();
            //判断用户是否存在
            UserAccount userAccount = userAccountRepo.findById(new UserAccountId(userId));
            if (userAccount == null) {
                throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
            }
            password = userAccount.getPassword();
        } else {
            List<UserAccount> userAccounts = userAccountRepo.findByUserNameAndType(cmd.getPhone(), 5);
            if (userAccounts.size() > 0) {
                throw new UcBizException(UcReturnCode.UC200035.getMsg(), UcReturnCode.UC200035.getCode());
            }

            userId = CommonSelfIdGenerator.generateId();
            password = BPwdEncoderUtils.BCryptPassword("123456");
        }
        UserAccount userAccount = UserAccount.builder()
                .userAccountId(new UserAccountId(userId))
                .shopId(shopId)
                .nickname(cmd.getNickname())
                .username(cmd.getPhone())
                .password(password)
                .type(5)
                .phone(cmd.getPhone())
                .userStatus(UserAccountStatusEnums.ACCOUNT_ENABLE)
                .build();

        if (cmd.getId() != null) {
            userAccountRepo.update(userAccount);
            //获取已设置的角色列表
            List<Long> exitRoleIds = roleRepo.getUserRoleIdList(userId);
            //获取已设置的菜单列表
            List<Long> exitMenuIds = roleRepo.getUserMenuIdList(userId, shopId);

            //删除的角色菜单
            List<Long> deleteRoleList = exitRoleIds.stream().filter(item -> !commitRoleIds.contains(item)).collect(toList());
            log.info("删除的角色菜单：{}", JSON.toJSONString(deleteRoleList));

            // 新增的角色菜单
            List<Long> addRoleList = commitRoleIds.stream().filter(item -> !exitRoleIds.contains(item)).collect(toList());
            log.info("新增的角色菜单：{}", JSON.toJSONString(addRoleList));

            if (addRoleList.size() > 0) {
                //保存新增的
                roleRepo.saveUserShopRole(addRoleList, userId);
            }
            if (deleteRoleList.size() > 0) {
                //删除不存在的
                roleRepo.deleteUserRole(deleteRoleList, userId);
            }

            //删除的权限菜单
            List<Long> deleteMenuList = exitMenuIds.stream().filter(item -> !commitMenuIds.contains(item)).collect(toList());
            log.info("删除权限菜单：{}", JSON.toJSONString(deleteMenuList));

            // 新增的角色菜单
            List<Long> addMenuList = commitMenuIds.stream().filter(item -> !exitMenuIds.contains(item)).collect(toList());
            log.info("新增的权限菜单：{}", JSON.toJSONString(addRoleList));

            if (addMenuList.size() > 0) {
                //保存新增的
                roleRepo.saveUserShopMenu(addMenuList, userId, shopId);
            }
            if (deleteMenuList.size() > 0) {
                //删除不存在的
                roleRepo.deleteUserMenu(deleteMenuList, userId, shopId);
            }

        } else {
            userAccountRepo.save(userAccount);
            //保存角色
            if (cmd.getRoleIds().size() > 0) {
                roleRepo.saveUserShopRole(commitRoleIds, userId);
            }
            //保存权限
            if (cmd.getMenuIds().size() > 0) {
                roleRepo.saveUserShopMenu(commitMenuIds, userId, shopId);
            }
        }
    }


    /**
     * 删除管理用户
     *
     * @param userAccountId
     */
    public void deleteManagerUser(UserAccountId userAccountId) {
        //判断用户是否存在
        UserAccount userAccount = userAccountRepo.findById(userAccountId);
        if (userAccount == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
        }
        userAccountRepo.deleteUserAccount(userAccountId);
    }
}
