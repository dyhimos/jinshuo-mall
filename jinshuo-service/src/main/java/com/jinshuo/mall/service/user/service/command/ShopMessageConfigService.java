package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.shopMessageConfig.ShopMessageConfig;
import com.jinshuo.mall.service.user.mybatis.ShopMessageConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 店铺短信配置信息
 * @Author: dongyh
 * @CreateDate: 2019/12/13 14:10
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/12/13 14:10
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
public class ShopMessageConfigService {

    @Autowired
    private ShopMessageConfigRepo shopMessageConfigRepo;


    /**
     * 根据店铺Id查询店铺短信配置信息
     *
     * @param shopId
     * @return
     */
    public ShopMessageConfig queryByShopId(Long shopId) {
        ShopMessageConfig shopMessageConfig = shopMessageConfigRepo.queryByShopId(shopId);
        if (shopMessageConfig == null) {
            throw new UcBizException(UcReturnCode.UC200033.getMsg(), UcReturnCode.UC200033.getCode());
        }
        return shopMessageConfig;
    }


    /**
     * 删除
     *
     * @param id
     */
    public int delete(Long id) {
        return shopMessageConfigRepo.delete(id);
    }
}
