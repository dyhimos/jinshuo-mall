package com.jinshuo.mall.service.user.application.cmd;

import lombok.Data;

/**
 * Created by 19458 on 2019/8/19.
 * 供应商管理员
 */
@Data
public class SupplierManagerCmd  {
    private Long id;
    private Long supplierId;//
    private String name;//姓名
    private String mobile;//手机号码
    private String idCard;//身份证号
    private Integer role;//角色
    private Integer sex;//性别
    private Long userAccountId;//登录信息id
    private String password;//密码
    private Integer supplierManagerStatus;//管理员状态 0正常 1停用
}
