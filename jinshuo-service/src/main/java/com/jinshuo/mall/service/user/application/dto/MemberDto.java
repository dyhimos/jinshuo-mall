package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员编号
 *
 * @author dongyh
 * @Title: MemberDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class MemberDto {

    @ApiModelProperty(value = "会员id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "客户号")
    private Long memNo;

    @ApiModelProperty(value = "客户名称")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "客户性别")
    private SexEnums sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "出生年月日")
    private String birthday;

    @ApiModelProperty(value = "会员等级")
    private Integer level;

    @ApiModelProperty(value = "会员类型:1、粉丝；2、会员；3、普通会员；4、分销员")
    private Integer type;

    @ApiModelProperty(value = "会员来源：1、微信；2、小程序；3、app；4、PC；5、手工录入")
    private Integer sourceCanal;

    @ApiModelProperty(value = "用户所在国家")
    private String country;

    @ApiModelProperty(value = "用户所在省份")
    private String province;

    @ApiModelProperty(value = "用户所在城市")
    private String city;

    @ApiModelProperty(value = "是否分销会员（1：是  2：不是）")
    private Integer isDis;

    @ApiModelProperty(value = "是否粉丝（1：是 0 :不是））")
    private Integer isFans;

    @ApiModelProperty(value = "上级用户")
    private Long pid;

    @ApiModelProperty(value = "消费总额")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "消费订单数")
    private Integer consumeOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "最近消费时间")
    private Date consumeTime;

    @ApiModelProperty(value = "父节点")
    private Member parentMember;

    /**
     * 父名称
     */
    private String parentName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 关注时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date subscribeTime;

    @ApiModelProperty(value = "支付宝账号")
    private String payNo;

    @ApiModelProperty(value = "微信号")
    private String wxNo;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickname;
}
