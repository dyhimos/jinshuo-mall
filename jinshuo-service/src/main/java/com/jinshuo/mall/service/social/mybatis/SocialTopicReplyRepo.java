package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopicReply;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialTopicReplyRepo {

    @Autowired
    private SocialTopicReplyMapper socialTopicReplyMapper;

    public int create(SocialTopicReply socialTopicReply) {
        return socialTopicReplyMapper.create(socialTopicReply);
    }

    public SocialTopicReply findById(Long id) {
        return socialTopicReplyMapper.queryById(id);
    }

    public int deleteById(Long id) {
        return socialTopicReplyMapper.deleteById(id);
    }

    public int review(SocialTopicReply socialTopicReply) {
        return socialTopicReplyMapper.review(socialTopicReply);
    }
}