package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopic;
import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialTopicRepo {

    @Autowired
    private SocialTopicMapper socialTopicMapper;

    public int create(SocialTopic socialTopic) {
        return socialTopicMapper.create(socialTopic);
    }

    public SocialTopic findById(Long id) {
        return socialTopicMapper.queryById(id);
    }

    public List<SocialTopic> queryTopicByExmple(SocialTopicQry qry) {
        return socialTopicMapper.queryTopicByExmple(qry);
    }

    public int deleteById(Long id) {
        return socialTopicMapper.deleteById(id);
    }

    public int update(SocialTopic socialTopic) {
        return socialTopicMapper.update(socialTopic);
    }

    public int up(SocialTopic socialTopic) {
        return socialTopicMapper.up(socialTopic);
    }

    public int collect(SocialTopic socialTopic) {
        return socialTopicMapper.collect(socialTopic);
    }

    public int reply(SocialTopic socialTopic) {
        return socialTopicMapper.reply(socialTopic);
    }

    public int review(SocialTopic socialTopic) {
        return socialTopicMapper.review(socialTopic);
    }

    public List<SocialTopic> queryMyCollecttopic(SocialTopicQry qry) {
        return socialTopicMapper.queryMyCollecttopic(qry);
    }

    public List<SocialTopic> quryMyUpedPage(Long userId) {
        return socialTopicMapper.quryMyUpedPage(userId);
    }

    public int recommendTopic(SocialTopic socialTopic) {
        return socialTopicMapper.recommendTopic(socialTopic);
    }
}