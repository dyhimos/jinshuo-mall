package com.jinshuo.mall.domain.user.model.shop;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends IdentifiedEntity {
    private ShopId shopId;
    private String name;
    private Long merchantId;
    private Integer type;
    private Integer shopStatus; //店铺状态 0正常  1停用

    /**
     * 免费说明
     */
    private String freeInstructions;

    /**
     * 注册协议
     */
    private String registAgreement;
    /**
     * 客服
     */
    private String customerService;

    /**
     * 买纱须知
     */
    private String buyWoolNeedKnow;

    /**
     * 店铺头像
     */
    private String logo;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 客服电话
     */
    private String customerTel;

    /**
     * 营业时间：1全天 2：自定义
     */
    private Integer workType;

    /**
     * 开始时间
     */
    private Date starTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 发布信息协议
     */
    private String publishAgreement;

    public Shop insert() {
        this.shopId = new ShopId(CommonSelfIdGenerator.generateId());
        this.shopStatus = 0;
        super.preInsert();
        return this;
    }
}
