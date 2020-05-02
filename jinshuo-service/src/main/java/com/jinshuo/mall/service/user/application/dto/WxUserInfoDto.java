package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信返回用户信息转化的dto
 * @author dongyh
 * @Title: WxUserInfoDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class WxUserInfoDto {
    public String openid;
    public Integer sex;
    public String nickname;
    public String gender;
    public String city;
    public String province;
    public String country;
    public String avatarUrl;
    public String unionId;

    /**
     * 公众号头像参数
     */
    public String headImgUrl;
}
