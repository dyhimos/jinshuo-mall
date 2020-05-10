package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.TopicShield;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicShieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 *
 * @author dyh
 */
@Repository
public class SocialTopicShieldRepo {

    @Autowired
    private SocialTopicShieldMapper socialTopicShieldMapper;

    public int create(TopicShield topicShield) {
        return socialTopicShieldMapper.create(topicShield);
    }

    public List<TopicShield> findByUserId(Long id) {
        return socialTopicShieldMapper.queryByUserId(id);
    }

    public int deleteById(Long id) {
        return socialTopicShieldMapper.deleteById(id);
    }

    public int deleteByUserId(Long id) {
        return socialTopicShieldMapper.deleteByUserId(id);
    }
}
