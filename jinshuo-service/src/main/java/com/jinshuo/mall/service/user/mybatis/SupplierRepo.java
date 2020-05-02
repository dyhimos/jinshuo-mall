package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.supplier.Supplier;
import com.jinshuo.mall.service.user.mybatis.mapper.SupplierMapper;
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
public class SupplierRepo {

    @Autowired(required = false)
    private SupplierMapper supplierMapper;

    public int save(Supplier supplier) {
        return supplierMapper.create(supplier);
    }

    public int update(Supplier supplier) {
        return supplierMapper.update(supplier);
    }

    public Supplier findById(Long id) {
        return supplierMapper.queryById(id);
    }

    public List<Supplier> findAll(Supplier supplier) {
        List<Supplier> list = supplierMapper.queryList(supplier);
        return list;
    }

    public int delete(Long id) {
        return supplierMapper.delete(id);
    }


    public List<Supplier> queryPageList(Supplier supplier) {
        List<Supplier> list = supplierMapper.queryPageList(supplier);
        return list;
    }

}
