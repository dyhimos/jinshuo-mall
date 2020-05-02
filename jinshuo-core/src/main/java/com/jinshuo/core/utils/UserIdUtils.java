package com.jinshuo.core.utils;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.model.UserAuthDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 19458 on 2019/8/9.
 */
@Slf4j
public class UserIdUtils {

    public static Long getUserId() {
        Long userId = null;
        try {
            //userId = UserUtils.getCurrentFrontUserId(UserIdUtils.JWTSECRET);
            userId = Long.valueOf(UserUtils.getMerchantUserInfo().getId());
        } catch (Exception e) {
            log.error("系統错误,",e);
            throw new IcBizException(IcReturnCode.IC201014.getCode(), IcReturnCode.IC201014.getMsg());
        }
        return userId;
    }

    public static UserAuthDto getUser() {
        UserAuthDto user = null;
        try {
            user = UserUtils.getMerchantUserInfo();
        } catch (Exception e) {
            log.error("系統错误,",e);
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
            log.error("系統错误,",e);
            throw new IcBizException(IcReturnCode.IC201014.getCode(), IcReturnCode.IC201014.getMsg());
        }
        return merchantId;
    }
}
