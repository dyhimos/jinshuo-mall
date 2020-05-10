package com.jinshuo.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.model.UserAuthDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by 19458 on 2019/8/9.
 */
@Slf4j
public class UserIdUtils {

    public static Long getUserId() {
        Long userId = null;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //userId = UserUtils.getCurrentFrontUserId(UserIdUtils.JWTSECRET);
            //userId = Long.valueOf(UserUtils.getMerchantUserInfo().getId());
            log.info(" -- user" + JSONObject.toJSONString(user));
            userId =  165475033416728576L;
            //user.getUsername();
            //SpringUtil.getBean();
        } catch (Exception e) {
            log.error("系統错误,", e);
            throw new IcBizException(IcReturnCode.IC201014.getCode(), IcReturnCode.IC201014.getMsg());
        }
        return userId;
    }

    public static UserAuthDto getUser() {
        UserAuthDto user = null;
        try {
            user = UserUtils.getMerchantUserInfo();
        } catch (Exception e) {
            log.error("系統错误,", e);
            throw new IcBizException(IcReturnCode.IC201014.getCode(), IcReturnCode.IC201014.getMsg());
        }
        return user;
    }


    public static Long getMerchantId() {
        Long merchantId = null;
        try {
            //merchantId = UserUtils.getMerchantUserInfo().getMerchantId();
            merchantId = 1l;
        } catch (Exception e) {
            log.error("系統错误,", e);
            throw new IcBizException(IcReturnCode.IC201014.getCode(), IcReturnCode.IC201014.getMsg());
        }
        return merchantId;
    }

    /**
     * 缓存中获取登录核销员信息
     *
     * @param
     * @return
     */
    public static SupplierManagerDto getSupplierByUserId() throws IcBizException {
        log.info(" -- 缓存中获取登录核销员信息,{}");
        SupplierManagerDto dto = null;
        try {
            String str = RedisUtil.getStr(UserIdUtils.getUserId().toString());
            JSONObject obj = JSONObject.parseObject(str);
            dto = (SupplierManagerDto) JSON.toJavaObject(obj, SupplierManagerDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" -- 缓存中获取登录核销员信息失败，错误信息：", e);
            throw new IcBizException(IcReturnCode.IC201014.getMsg());
        }
        return dto;
    }


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
}
