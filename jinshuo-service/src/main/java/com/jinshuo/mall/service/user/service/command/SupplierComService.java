package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.supplier.Supplier;
import com.jinshuo.mall.domain.user.model.supplier.SupplierId;
import com.jinshuo.mall.service.user.application.cmd.SupplierCmd;
import com.jinshuo.mall.service.user.mybatis.SupplierRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SupplierComService {

    @Autowired
    private SupplierRepo supplierRepo;


    /**
     * 保存
     *
     * @param cmd
     */
    public int save(SupplierCmd cmd) {
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        if (null == cmd.getLoginFlag()) {
            cmd.setLoginFlag(1);
        }
        Supplier supplier = new Supplier();
        if (null == cmd.getId()) {
            cmd.setId(CommonSelfIdGenerator.generateId());
            BeanUtils.copyProperties(cmd, supplier);
            supplier.insert();
            return supplierRepo.save(supplier);
        } else {
            BeanUtils.copyProperties(cmd, supplier);
            supplier.setSupplierId(new SupplierId(cmd.getId()));
            return supplierRepo.update(supplier);
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public int delete(Long id) {
        return supplierRepo.delete(id);
    }
}
