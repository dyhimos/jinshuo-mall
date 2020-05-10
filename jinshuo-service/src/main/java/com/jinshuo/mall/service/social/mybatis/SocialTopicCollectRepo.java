package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopicCollect;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialTopicCollectRepo {

    @Autowired
    private SocialTopicCollectMapper socialTopicCollectMapper;

    public int create(SocialTopicCollect socialTopicCollect) {
        return socialTopicCollectMapper.create(socialTopicCollect);
    }

    public SocialTopicCollect findById(Long id) {
        return socialTopicCollectMapper.queryById(id);
    }

    public int deleteById(Long id) {
        return socialTopicCollectMapper.deleteById(id);
    }

    public SocialTopicCollect findByType(Long userId, Long topicId) {
        return socialTopicCollectMapper.findByType(userId,topicId);
    }
}