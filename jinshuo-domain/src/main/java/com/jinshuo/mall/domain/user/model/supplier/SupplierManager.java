package com.jinshuo.mall.domain.user.model.supplier;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by 19458 on 2019/8/19.
 * 供应商管理员
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierManager extends IdentifiedEntity {

    private SupplierManagerId supplierManagerId;
    private Long supplierId;//
    private String name;//姓名
    private String mobile;//手机号码
    private String idCard;//身份证号
    private Integer role;//角色
    private Integer sex;//性别
    private Long userAccountId;//登录信息id
    private Integer supplierManagerStatus;//管理员状态 0正产  1停用

    public SupplierManager insert() {
        this.supplierManagerId = new SupplierManagerId(CommonSelfIdGenerator.generateId());
        this.supplierManagerStatus = 0;
        super.preInsert();
        return this;
    }

    public SupplierManager update() {
        this.updateDate = new Date();
        return this;
    }

    public SupplierManager update(String name, String mobile, String idCard, Integer role, Integer sex, Integer supplierManagerStatus) {
        this.name = name;
        this.mobile = mobile;
        this.idCard = idCard;
        this.role = role;
        this.sex = sex;
        if (null != supplierManagerStatus) {
            this.supplierManagerStatus = supplierManagerStatus;
        } else {
            this.supplierManagerStatus = 0;
        }
        update();
        return this;
    }
}
