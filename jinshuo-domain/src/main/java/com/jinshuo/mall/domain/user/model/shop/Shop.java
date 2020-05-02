package com.jinshuo.mall.domain.user.model.shop;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by 19458 on 2019/9/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends IdentifiedEntity {
    private ShopId shopId;
    private String name;
    private Long merchantId;
    private Integer type;
    private Integer shopStatus; //店铺状态 0正常  1停用

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



    public Shop insert() {
        this.shopId = new ShopId(CommonSelfIdGenerator.generateId());
        this.shopStatus = 0;
        super.preInsert();
        return this;
    }
}
