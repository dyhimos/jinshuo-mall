package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.mall.domain.user.model.merchant.Merchant;
import com.jinshuo.mall.domain.user.model.merchantlogin.MerchantManager;
import com.jinshuo.mall.service.user.mybatis.MerchantManagerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/11/25.
 */
@Slf4j
@Service
public class MerchantManagerQueryService {

    @Autowired
    private MerchantManagerRepo merchantManagerRepo;

    @Autowired
    private MerchantQueryService merchantQueryService;


    public MerchantManager getByUserId(Long userId) {
        return merchantManagerRepo.findByUserId(userId);
    }


    public Merchant getMerchantByUserId(Long userId) {
        MerchantManager merchantManager = getByUserId(userId);
        if (null == merchantManager || null == merchantManager.getMerchantId()) {
            return null;
        }
        return merchantQueryService.getById(merchantManager.getMerchantId().getId());
    }
}
