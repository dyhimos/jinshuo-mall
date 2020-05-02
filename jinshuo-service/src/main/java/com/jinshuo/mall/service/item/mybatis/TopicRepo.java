package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.topic.Topic;
import com.jinshuo.mall.service.item.mybatis.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class TopicRepo {

    @Autowired(required = false)
    private TopicMapper topicMapper;

    public void save(Topic topic) {
        topicMapper.create(topic);
    }

    public void update(Topic topic) {
        topicMapper.update(topic);
    }

    public Topic queryById(Long topicId) {
        return topicMapper.queryById(topicId);
    }

    public Topic queryByCode(String code,Long shopId) {
        return topicMapper.queryByCode(code,shopId);
    }


    public List<Topic> findAll(Topic temp) {
        List<Topic> list = topicMapper.findAll(temp);
        return list;
    }

    public List<Topic> findByPid(Long id) {
        return  topicMapper.queryByPid(id);
    }

    public int delete(Long id) {
        return topicMapper.delete(id);
    }

    public int updateStatus(Long id,Integer adStatus) {
        return topicMapper.updateStatus(id,adStatus);
    }
}
