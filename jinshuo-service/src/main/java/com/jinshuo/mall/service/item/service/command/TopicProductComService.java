package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.mall.domain.item.topic.TopicProduct;
import com.jinshuo.mall.service.item.application.cmd.TopicAddProductCmd;
import com.jinshuo.mall.service.item.application.cmd.TopicProductCmd;
import com.jinshuo.mall.service.item.mybatis.TopicProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19458 on 2019/7/19.
 */
@Slf4j
@Service
public class TopicProductComService {

    @Autowired
    private TopicProductRepo topicProductRepo;


    /**
     * 活动添加商品
     *
     * @param cmd
     */
    public int create(TopicAddProductCmd cmd) {
        log.info(" -- 活动添加商品,输入参数：" + JSONObject.toJSONString(cmd));
        List<TopicProduct> result = new ArrayList<>();
        TopicProduct topicProduct;
        for (TopicProductCmd topicProductCmd : cmd.getList()) {
            topicProduct = new TopicProduct();
            topicProduct.build(cmd.getTopicId(), topicProductCmd.getSpuId(), topicProductCmd.getSort());
            result.add(topicProduct);
        }
        topicProductRepo.saveList(result);
        return cmd.getList().size();
    }

    public int delete(Long id){
        topicProductRepo.delete(id);
        return 0;
    }

    public int updateProductSort(Long id,Integer sort){
        return topicProductRepo.updateProductSort(id, sort);
    }


}
