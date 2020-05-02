package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.mall.domain.item.feature.Feature;
import com.jinshuo.mall.service.item.application.cmd.FeatureCmd;
import com.jinshuo.mall.service.item.mybatis.FeatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class FeatureComService {

    @Autowired
    private FeatureRepo featureRepo;


    /**
     * 新增产品特征
     *
     * @param cmd
     */
    public int save(FeatureCmd cmd) {
        Feature feature = Feature.builder()
                .name(cmd.getName())
                .shopId(cmd.getShopId())
                .merchantId(1000011l)
                .sort(cmd.getSort())
                .build();
        if (null == cmd.getId()) {
            feature.insert();
            return featureRepo.save(feature);
        } else {
            feature.update(cmd.getId());
            return featureRepo.update(feature);
        }
    }


    public int delete(Long id) {
        return featureRepo.delete(id);
    }
}
