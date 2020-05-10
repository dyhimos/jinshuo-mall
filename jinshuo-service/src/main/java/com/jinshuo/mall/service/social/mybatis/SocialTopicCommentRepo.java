package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.topic.SocialTopicComment;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialTopicCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialTopicCommentRepo {

    @Autowired
    private SocialTopicCommentMapper socialTopicCommentMapper;

    public int create(SocialTopicComment socialTopicComment) {
        return socialTopicCommentMapper.create(socialTopicComment);
    }

    public SocialTopicComment findById(Long id) {
        return socialTopicCommentMapper.queryById(id);
    }

    public List<SocialTopicComment> findByTopicId(Long topicId) {
        return socialTopicCommentMapper.queryByTopicId(topicId);
    }

    public List<SocialTopicComment> findByExmple(SocialTopicComment socialTopicComment) {
        return socialTopicCommentMapper.findByExmple(socialTopicComment);
    }

    public int deleteById(Long id) {
        return socialTopicCommentMapper.deleteById(id);
    }

    public int review(SocialTopicComment socialTopicComment) {
        return socialTopicCommentMapper.review(socialTopicComment);
    }

    public List<SocialTopicComment> findReviewedByTopicId(Long topicId) {
        return socialTopicCommentMapper.findReviewedByTopicId(topicId);
    }

    public int up(SocialTopicComment socialTopicComment) {
        return socialTopicCommentMapper.up(socialTopicComment);
    }

    public List<SocialTopicComment> findByUserId(Long userId) {
        return socialTopicCommentMapper.queryByUserId(userId);
    }
}