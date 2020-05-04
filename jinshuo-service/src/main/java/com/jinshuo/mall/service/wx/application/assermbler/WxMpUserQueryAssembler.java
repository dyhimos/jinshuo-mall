package com.jinshuo.mall.service.wx.application.assermbler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19458 on 2019/9/2.
 */
@Data
public class WxMpUserQueryAssembler {

    /**
     * 将微信获取到的openid list 转为为批量请求用户信息JSON
     *
     * @param openidsList openid列表
     * @return
     */
    public static String assembleToRequestINfoList(List<String> openidsList) {
        JSONObject userListObject = new JSONObject();

        List<JSONObject> userList = new ArrayList<>();
        for (String openid : openidsList) {
            JSONObject openidObject = new JSONObject();
            openidObject.put("openid", openid);
            openidObject.put("lang", "zh_CN");
            userList.add(openidObject);
        }
        userListObject.put("user_list", userList);
        String json = JSON.toJSONString(userListObject);
        return json;
    }
}
