package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.service.ServiceSupport;
import com.jinshuo.mall.service.item.mybatis.mapper.ServiceSupportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class ServiceSupportRepo {

    @Autowired(required = false)
    private ServiceSupportMapper serviceSupportMapper;

    public void save(ServiceSupport serviceSupport) {
        serviceSupportMapper.create(serviceSupport);
    }

    public List<ServiceSupport> findAll(ServiceSupport serviceSupport) {
        return serviceSupportMapper.findAll(serviceSupport);
    }

    public int update(ServiceSupport serviceSupport) {
        return serviceSupportMapper.update(serviceSupport);
    }

    public int updateStatus(Long id, Integer status) {
        return serviceSupportMapper.updateStatus(id, status);
    }

    public int delete(Long id) {
        return serviceSupportMapper.delete(id);
    }

    public int updateProductSort(Long id, Integer sort) {
        return serviceSupportMapper.updateProductSort(id, sort);
    }
}
