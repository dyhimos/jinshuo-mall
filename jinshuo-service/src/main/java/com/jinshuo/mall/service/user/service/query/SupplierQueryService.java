package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.user.model.supplier.Supplier;
import com.jinshuo.mall.service.user.application.assermbler.SupplierAssembler;
import com.jinshuo.mall.service.user.application.dto.SupplierDto;
import com.jinshuo.mall.service.user.application.qry.SupplierQry;
import com.jinshuo.mall.service.user.mybatis.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SupplierQueryService {


    @Autowired
    private SupplierRepo supplierRepo;


    /**
     * 分页查询供应商
     *
     * @param qry
     * @return Address
     */
    public PageInfo getByPage(SupplierQry qry) {
        if (null == qry.getShopId()) {
            qry.setShopId(10088L);
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Supplier temp = Supplier.builder().shopId(qry.getShopId()).build();
        List<Supplier> list = supplierRepo.queryPageList(temp);
        PageInfo pageInfo = new PageInfo<>(list);
        List<SupplierDto> dtos = list.stream().map(supplier -> SupplierAssembler.assembleSupplierDto(supplier)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 查询供应商集合
     *
     * @param
     * @return Address
     */
    public List<SupplierDto> getList() {
        Supplier temp = Supplier.builder().shopId(10088L).build();
        List<Supplier> list = supplierRepo.findAll(temp);
        List<SupplierDto> dtos = list.stream().map(supplier -> SupplierAssembler.assembleSupplierDto(supplier)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * 根据id查询供应商
     *
     * @param
     * @return Address
     */
    public SupplierDto getById(Long id) {
        Supplier supplier = supplierRepo.findById(id);
        return SupplierAssembler.assembleSupplierDto(supplier);
    }
}
