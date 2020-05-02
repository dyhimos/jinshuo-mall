package com.jinshuo.mall.service.user.service.query;


import com.jinshuo.mall.domain.user.model.merchant.Merchant;
import com.jinshuo.mall.service.user.mybatis.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class MerchantQueryService {


    @Autowired
    private MerchantRepo merchantRepo;


    /**
     * 根据id查询商家信息
     *
     * @return Address
     */
    public Merchant getById(Long id) {
        return merchantRepo.findById(id);
    }


    /**
     * 根据id查询商家信息
     *
     * @return Address
     */
    public Merchant findByUserId(Long id) {
        return merchantRepo.findByUserId(id);
    }


}
