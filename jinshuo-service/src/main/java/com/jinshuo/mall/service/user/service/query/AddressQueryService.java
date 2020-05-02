package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.address.Address;
import com.jinshuo.mall.service.user.application.assermbler.AddressAssembler;
import com.jinshuo.mall.service.user.application.dto.AddressDto;
import com.jinshuo.mall.service.user.application.qry.AddressQry;
import com.jinshuo.mall.service.user.mybatis.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class AddressQueryService {


    @Autowired
    private AddressRepo addressRepo;


    /**
     * 查询我的默认地址
     *
     * @return Address
     */
    public AddressDto getDefault() {
        return AddressAssembler.assembleAddressDto(addressRepo.queryDefault(UserIdUtils.getUserId()));
    }


    /**
     * 分页查询我的默认地址
     *
     * @param qry
     * @return Address
     */
    public PageInfo getByPage(AddressQry qry) {
        log.info(" -- 分页查询我的默认地址 输入参数，{}", qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Address> list = addressRepo.getByPage(UserIdUtils.getUserId());
        List<AddressDto> dtos = list.stream().map(address -> AddressAssembler.assembleAddressDto(address)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(dtos);
        return pageInfo;
    }

    public AddressDto getAddressById(Long id) {
        return AddressAssembler.assembleAddressDto(addressRepo.findById(id));
    }

    public Address getById(Long id) {
        return addressRepo.findById(id);
    }
}
