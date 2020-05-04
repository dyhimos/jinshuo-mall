package com.jinshuo.mall.domain.user.model.wxOpenAuth;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author mgh
 * @Classname WxMenu
 * @Description 微信菜单
 * @Date 2019/6/16 :01
 * @Created by mgh
 */
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenAuth extends IdentifiedEntity {

    /**
     * id
     */
    private WxOpenAuthId wxOpenAuthId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 授权方appid
     */
    private String appid;

    /**
     * 刷新令牌
     */
    private String authorizerRefreshToken;


    /**
     * 根据店铺id查询授权的公众号信息
     *
     * @param shopId
     */
    public static WxOpenAuth getWxAuth(Long shopId) {
        /*WxOpenAuth wxOpenAuth = SpringUtil.getBean(WxOpenAuthRepo.class).findByShopId(shopId);
        if(wxOpenAuth==null){
            throw new UcBizException(UcReturnCode.UC200012.getMsg(),UcReturnCode.UC200012.getCode());
        }*/
        return null;
    }
}
