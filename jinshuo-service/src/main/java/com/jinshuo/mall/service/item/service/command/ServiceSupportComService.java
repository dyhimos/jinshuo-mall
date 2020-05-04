package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.mall.domain.item.service.ServiceSupport;
import com.jinshuo.mall.domain.item.service.ServiceSupportId;
import com.jinshuo.mall.service.item.application.cmd.ServiceSupportCmd;
import com.jinshuo.mall.service.item.mybatis.ServiceSupportRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/19.
 */
@Slf4j
@Service
public class ServiceSupportComService {

    @Autowired
    private ServiceSupportRepo serviceSupportRepo;


    /**
     * 活动添加商品
     *
     * @param cmd
     */
    /**
     * 保存活动
     *
     * @param cmd
     */
    public int create(ServiceSupportCmd cmd) {
        cmd.setShopId(DefaultShopId.SHOPID);
        if (null != cmd.getId()) {
            update(cmd);
        } else {
            ServiceSupport serviceSupport = ServiceSupport.builder()
                    .shopId(cmd.getShopId())
                    .name(cmd.getName())
                    .desc(cmd.getDesc())
                    .sort(cmd.getSort())
                    .build();
            serviceSupport.insert();
            serviceSupportRepo.save(serviceSupport);
        }
        return 1;
    }

    /**
     * 修改活动
     *
     * @param cmd
     */
    public void update(ServiceSupportCmd cmd) {
        ServiceSupport serviceSupport = ServiceSupport.builder()
                .serviceSupportId(new ServiceSupportId(cmd.getId()))
                .shopId(cmd.getShopId())
                .name(cmd.getName())
                .desc(cmd.getDesc())
                .sort(cmd.getSort())
                .build();
        serviceSupportRepo.update(serviceSupport);
    }

    /**
     * 删除活动
     *
     * @param id
     * @return
     */

    public int delete(Long id) {
        return serviceSupportRepo.delete(id);
    }

    /**
     * 修改活动状态
     *
     * @param id
     * @return
     */
    public int updateStatus(Long id, Integer adStatus) {
        return serviceSupportRepo.updateStatus(id, adStatus);
    }


}
