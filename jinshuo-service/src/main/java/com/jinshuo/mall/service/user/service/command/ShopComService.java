package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.shop.Shop;
import com.jinshuo.mall.domain.user.model.shop.ShopId;
import com.jinshuo.mall.service.user.application.cmd.ShopCmd;
import com.jinshuo.mall.service.user.mybatis.ShopRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class ShopComService {

    @Autowired
    private ShopRepo shopRepo;


    /**
     * 保存
     *
     * @param cmd
     */
    public int save(ShopCmd cmd) {
        Shop shop = new Shop();
        if (null == cmd.getId()) {
            cmd.setId(CommonSelfIdGenerator.generateId());
            BeanUtils.copyProperties(cmd, shop);
            shop.insert();
            return shopRepo.save(shop);
        } else {
            shop = shopRepo.findById(cmd.getId());
            //判断店铺是否存在
            if (shop == null) {
                throw new UcBizException(UcReturnCode.UC200042.getMsg(), UcReturnCode.UC200042.getCode());
            }
            BeanUtils.copyProperties(cmd, shop);
            shop.setShopId(new ShopId(cmd.getId()));
            shop.setUpdateDate(new Date());
            return shopRepo.update(shop);
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public int delete(Long id) {
        return shopRepo.delete(id);
    }
}
