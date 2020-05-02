package com.jinshuo.mall.domain.user.model.supplier;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/8/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends IdentifiedEntity {
    private SupplierId supplierId;//
    private Long userId;//user_id
    private String supplierCode;//供应商号码
    private String iconUrl;//供应商图标
    private String supplierName;//供应商名称
    private Integer supplierType;//供应商类型
    private String linkMan;//联系人
    private String phoneNumber;//联系电话
    private String province;//所在省
    private String city;//所在市
    private String disc;//所在区
    private String address;//详情地址
    private String desc;//供应商介绍1、平台商家；2、度假酒店；3、景点票类；4、亲子乐园；4、特产商家
    private Integer supplierStatus;//供应商状态状态：1冻结，0正常
    private Long shopId;
    private Integer loginFlag; // 0-> 可登陆  1->不可登陆

    public Supplier insert() {
        this.supplierId = new SupplierId(CommonSelfIdGenerator.generateId());
        this.supplierStatus = 0;
        this.supplierCode = String.valueOf(System.currentTimeMillis());
        super.preInsert();
        return this;
    }
}
