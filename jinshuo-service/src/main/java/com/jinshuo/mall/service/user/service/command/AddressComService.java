package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.mall.domain.user.model.address.Address;
import com.jinshuo.mall.service.user.application.cmd.AddressCmd;
import com.jinshuo.mall.service.user.application.cmd.AddressCreateCmd;
import com.jinshuo.mall.service.user.mybatis.AddressRepo;
import com.jinshuo.mall.service.user.service.query.AddressQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class AddressComService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private AddressQueryService addressQueryService;

    /**
     * 保存
     *
     * @param cmd
     */
    public int save(AddressCreateCmd cmd) {
        Address address = new Address();
        BeanUtils.copyProperties(cmd, address);
        address.insert();
        return addressRepo.save(address);
    }

    public int delete(Long id) {
        return addressRepo.delete(id);
    }

    /**
     * 修改
     *
     * @param cmd
     */
    public int update(AddressCmd cmd) {
        log.info(" -- 修改地址，输入参数：" + JSONObject.toJSONString(cmd));
        Address address = addressQueryService.getById(cmd.getId());
        BeanUtils.copyProperties(cmd, address);
        address.update();
        return addressRepo.update(address);
    }
}
