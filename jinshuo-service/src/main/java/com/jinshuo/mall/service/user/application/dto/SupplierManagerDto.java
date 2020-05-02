package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/19.
 * 供应商管理员
 */
@Data
public class SupplierManagerDto  {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long supplierId;//
    private String supplierName;//
    private String name;//姓名
    private String mobile;//手机号码
    private String idCard;//身份证号
    private Integer role;//角色
    private Integer sex;//性别

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userAccountId;//登录信息id
    private Integer supplierManagerStatus;//管理员状态

}
