package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.topic.TopicProduct;
import com.jinshuo.mall.service.item.mybatis.mapper.TopicProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class TopicProductRepo {

    @Autowired(required = false)
    private TopicProductMapper topicProductMapper;

    public void save(TopicProduct topicProduct) {
        topicProductMapper.create(topicProduct);
    }

    public void saveList(List<TopicProduct> topicProducts) {
        topicProductMapper.createList(topicProducts);
    }

    public List<TopicProduct> findAll(Long topicId) {
        List<TopicProduct> list = topicProductMapper.findAll(topicId);
        return list;
    }

    public int delete(Long id) {
        return topicProductMapper.delete(id);
    }

    public int deleteByTopicId(Long topicId) {
        return topicProductMapper.deleteByTopicId(topicId);
    }

    public int updateProductSort(Long id,Integer sort) {
        return topicProductMapper.updateProductSort(id,sort);
    }
}
