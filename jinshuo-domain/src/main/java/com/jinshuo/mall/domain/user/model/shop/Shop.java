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
    private String logo;
    private String linkMan;
    private String phone;
    private String sketch;
    private Date startTime;
    private Date endTime;

    public Shop insert() {
        this.shopId = new ShopId(CommonSelfIdGenerator.generateId());
        this.shopStatus = 0;
        super.preInsert();
        return this;
    }
}
