package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.address.Address;
import com.jinshuo.mall.service.user.mybatis.mapper.AddressMapper;
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
public class AddressRepo {

    @Autowired(required = false)
    private AddressMapper addressMapper;


    public int save(Address address) {
        return addressMapper.create(address);
    }

    public int update(Address address) {
        return addressMapper.update(address);
    }

    public Address queryDefault(Long id) {
        return addressMapper.queryDefault(id);
    }

    public List<Address> findAll(Long memId) {
        List<Address> list = addressMapper.findAll(memId);
        return list;
    }

    public int delete(Long id) {
        return addressMapper.delete(id);
    }

    public List<Address> getByPage(Long memId) {
        return addressMapper.findAll(memId);
    }

    public Address findById(Long id) {
        return addressMapper.findById(id);
    }

}
