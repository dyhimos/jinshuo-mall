package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.mall.domain.user.model.wx.WxConfig;
import com.jinshuo.mall.domain.user.model.wxMenu.WxMenu;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.application.cmd.WxCreateQrcodeCmd;
import com.jinshuo.mall.service.user.mybatis.WxMenuRepo;
import com.jinshuo.mall.service.wx.application.cmd.WxActionInfoCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxQrcodeCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxSenceInfoCmd;
import com.jinshuo.mall.service.wx.application.service.WxConfigCmdService;
import com.jinshuo.mall.service.wx.application.service.WxMp;
import com.jinshuo.mall.service.wx.utils.AccessTokenUtils;
import com.jinshuo.mall.service.wx.webservice.WxMpAgentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Description:    微信菜单services
* @Author:         mgh
* @CreateDate:     2019/9/18 16:58
* @UpdateUser:     mgh
* @UpdateDate:     2019/9/18 16:58
* @UpdateRemark:
* @Version:        1.0
*/
@Slf4j
@Service
public class WxMenuService {

    @Autowired
    private WxConfigCmdService wxConfigCmdService;

    @Autowired
    private WxMenuRepo wxMenuRepo;

    /**
     * 获取微信菜单信息
     * @param shopIdCmd
     * @return
     */
    public void createMenu(ShopIdCmd shopIdCmd) throws Exception {
        log.info("进入创建菜单：{}"+ JSON.toJSONString(shopIdCmd));
        WxConfig wxConfig = wxConfigCmdService.getWxConfig(shopIdCmd);
        //String token = wxMpUserService.getToken(wxConfig);

        String token = AccessTokenUtils.getOpenToken(shopIdCmd.getShopId());
        List<WxMenu> wxMenuList = wxMenuRepo.findByShopId(wxConfig.getShopId());
        JSONObject body = new JSONObject();
        List<JSONObject> firstMenu = new ArrayList<JSONObject>();;
        //查询所有的1级菜单
        for (WxMenu wxMenu : wxMenuList) {
            JSONObject firstMenuObject = new JSONObject();
            //根据id查询子菜单列表
            List<WxMenu> childMenuList = wxMenu.getChildMenuList();
            List<JSONObject> sub_button = new ArrayList<JSONObject>();
            for (WxMenu secondMenu : childMenuList) {
                JSONObject secondMenuObject = new JSONObject();
                secondMenuObject.put("type", secondMenu.getType());
                secondMenuObject.put("name", secondMenu.getName());
                switch (secondMenu.getType()){
                    case "view":secondMenuObject.put("url", secondMenu.getUrl());break;
                    case "click":secondMenuObject.put("key", secondMenu.getUrl());break;
                    default:
                }
                sub_button.add(secondMenuObject);
            }
            firstMenuObject.put("name", wxMenu.getName());
            if(childMenuList.size()==0){
                firstMenuObject.put("type", wxMenu.getType());
                switch (wxMenu.getType()){
                    case "view":firstMenuObject.put("url", wxMenu.getUrl());break;
                    case "click":firstMenuObject.put("key", wxMenu.getUrl());break;
                    default:
                }
            }else{
                firstMenuObject.put("sub_button", sub_button);
            }
            firstMenu.add(firstMenuObject);
        }
        body.put("button", firstMenu);
        log.info("创建菜单数据为：{}",body.toJSONString());
        WxMp.createMenu(token,body.toJSONString());
    }


    /**
     * 获取微信临时二维码
     * @param wxCreateQrcodeCmd
     * @return
     * @throws Exception
     */
    public String createQrcode(WxCreateQrcodeCmd wxCreateQrcodeCmd) throws Exception {
        //公众平台acctoken
        //String token = AccessTokenUtils.getWxMpToken(wxCreateQrcodeCmd.getShopId());
        String token = AccessTokenUtils.getOpenToken(wxCreateQrcodeCmd.getShopId());
        String sceneStr = wxCreateQrcodeCmd.getInviteCode();
        if(StringUtils.isNotBlank(wxCreateQrcodeCmd.getUrl())){
            sceneStr = sceneStr+","+wxCreateQrcodeCmd.getUrl();
        }
        WxSenceInfoCmd wxSenceInfoCmd = new WxSenceInfoCmd(sceneStr);

        WxActionInfoCmd wxActionInfoCmd = WxActionInfoCmd.builder()
                .scene(wxSenceInfoCmd)
                .build();

        WxQrcodeCmd wxQrcodeCmd =WxQrcodeCmd.builder()
                .expire_seconds(604800L)
                .action_name("QR_STR_SCENE")
                .action_info(wxActionInfoCmd)
                .build();
        log.info("生成二维码post参数：{}", JSON.toJSONString(wxQrcodeCmd));
        String url = WxMpAgentService.createQrcode(token,wxQrcodeCmd);
        return url;
    }
}
