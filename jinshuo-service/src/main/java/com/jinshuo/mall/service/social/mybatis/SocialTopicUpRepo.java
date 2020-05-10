package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopicUp;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 *
 * @author dyh
 */
@Repository
public class SocialTopicUpRepo {

    @Autowired
    private SocialTopicUpMapper socialTopicUpMapper;

    public int create(SocialTopicUp socialTopicUp) {
        return socialTopicUpMapper.create(socialTopicUp);
    }

    public SocialTopicUp findById(Long id) {
        return socialTopicUpMapper.queryById(id);
    }

    public SocialTopicUp findByType(Long userId, Long topicId) {
        return socialTopicUpMapper.findByType(userId, topicId);
    }

    public int deleteById(Long id) {
        return socialTopicUpMapper.deleteById(id);
    }

    public List<SocialTopicUp> findByUserId(Long userId,Integer operateType) {
        return socialTopicUpMapper.queryByUserId(userId,operateType);
    }
}