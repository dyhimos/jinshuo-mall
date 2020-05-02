package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.merchant.Merchant;
import com.jinshuo.mall.service.user.mybatis.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class MerchantRepo {

    @Autowired(required = false)
    private MerchantMapper merchantMapper;

    public Merchant findById(Long merchantId) {
        return merchantMapper.queryById(merchantId);
    }

    public Merchant findByUserId(Long userId) {
        return merchantMapper.findByUserId(userId);
    }
}
