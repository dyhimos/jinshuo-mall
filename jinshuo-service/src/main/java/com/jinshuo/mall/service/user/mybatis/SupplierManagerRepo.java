package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import com.jinshuo.mall.service.user.mybatis.mapper.SupplierManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class SupplierManagerRepo {

    @Autowired(required = false)
    private SupplierManagerMapper supplierManagerMapper;

    public int save(SupplierManager supplierManager) {
        return supplierManagerMapper.create(supplierManager);
    }

    public int update(SupplierManager supplierManager) {
        return supplierManagerMapper.update(supplierManager);
    }

    public List<SupplierManager> findAll(Long supplierId) {
        return supplierManagerMapper.findAll(supplierId);
    }

    public int delete(Long supplierManagerId) {
        return supplierManagerMapper.delete(supplierManagerId);
    }

    public SupplierManager queryById(Long supplierManagerId) {
        return supplierManagerMapper.queryById(supplierManagerId);
    }

    public SupplierManager queryByUserAccountId(Long supplierManagerId) {
        return supplierManagerMapper.queryByUserAccountId(supplierManagerId);
    }
}
