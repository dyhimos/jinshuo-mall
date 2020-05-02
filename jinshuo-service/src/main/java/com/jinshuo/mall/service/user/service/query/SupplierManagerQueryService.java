package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import com.jinshuo.mall.service.user.application.assermbler.SupplierManagerAssembler;
import com.jinshuo.mall.service.user.application.dto.SupplierManagerDto;
import com.jinshuo.mall.service.user.mybatis.SupplierManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SupplierManagerQueryService {


    @Autowired
    private SupplierManagerRepo supplierManagerRepo;


    /**
     * 分页查询供应商
     *
     * @param supplierId
     * @return Address
     */
    public List<SupplierManagerDto> getBySupplierId(Long supplierId) {
         List<SupplierManager> list = supplierManagerRepo.findAll(supplierId);
        List<SupplierManagerDto> dtos = list.stream().map(supplierManager -> SupplierManagerAssembler.assembleDto(supplierManager)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * 查询供应商
     *
     * @param id
     * @return Address
     */
    public SupplierManager getById(Long id) {
        return supplierManagerRepo.queryById(id);
    }

    public SupplierManager getByUserId(Long userId){
        return supplierManagerRepo.queryByUserAccountId(userId);
    }

}
