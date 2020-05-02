package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.merchantlogin.MerchantManager;
import com.jinshuo.mall.service.user.mybatis.mapper.MerchantManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商家用户表
 * @Classname MerchantLoginRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class MerchantManagerRepo {

    @Autowired(required = false)
    private MerchantManagerMapper mapper;

    /**
     * 保存商家管理员
     * @param merchantManager
     * @return
     */
    public int save(MerchantManager merchantManager) {
        return mapper.save(merchantManager);
    }

    /**
     * 根据userId查询merchantId
     * @param userId
     * @return
     */
    public MerchantManager findByUserId(Long  userId) {
        return mapper.queryByUserId(userId);
    }
}
