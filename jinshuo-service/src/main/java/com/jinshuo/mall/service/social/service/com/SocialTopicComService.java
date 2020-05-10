package com.jinshuo.mall.service.social.service.com;

import com.jinshuo.core.exception.sc.ScBizException;
import com.jinshuo.core.exception.sc.ScReturnCode;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.social.topic.*;
import com.jinshuo.mall.service.social.application.cmd.*;
import com.jinshuo.mall.service.social.mybatis.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
@Slf4j
@Service
public class SocialTopicComService {

    @Autowired
    private SocialTopicRepo socialTopicRepo;

    @Autowired
    private SocialTopicUpRepo socialTopicUpRepo;

    @Autowired
    private SocialTopicCollectRepo socialTopicCollectRepo;

    @Autowired
    private SocialTopicAlbumRepo socialTopicAlbumRepo;

    @Autowired
    private SocialTopicCommentRepo socialTopicCommentRepo;

    @Autowired
    private SocialTopicReplyRepo socialTopicReplyRepo;

    @Autowired
    private SocialTopicShieldRepo socialTopicShieldRepo;


    /**
     * 保存帖子
     */
    public SocialTopic saveTopic(SocialTopicCmd cmd, Integer userType) {
        log.info(" -- 保存帖子,输入参数，{}", cmd);
        SocialTopic socialTopic;
        if (null != cmd.getId()) {
            socialTopic = socialTopicRepo.findById(cmd.getId());
            if (null == socialTopic) {
                throw new ScBizException(ScReturnCode.IC209001.getCode(), ScReturnCode.IC209001.getMsg());
            }
            socialTopic.update(cmd.getTitle(), cmd.getContents());
            socialTopicRepo.update(socialTopic);
        } else {
            socialTopic = SocialTopic.builder()
                    .userId(UserIdUtils.getUserId())
                    .userType(userType)
                    .shopId(UserIdUtils.getUser().getShopId())
                    .title(cmd.getTitle())
                    .contents(cmd.getContents())
                    .attr(cmd.getAttr())
                    .circleId(cmd.getCircleId())
                    .build();
            socialTopic.init();
            socialTopicRepo.create(socialTopic);
            saveAlbum(socialTopic, cmd.getUrls());
        }
        return socialTopic;
    }


    /**
     * 评论
     *
     * @param cmd
     */
    public void commenttopic(SocialTopicCommentCmd cmd) {
        log.info(" -- 评论帖子,输入参数，{}", cmd);
        SocialTopic socialTopic = socialTopicRepo.findById(cmd.getTopicId());
        if (null == socialTopic) {
            return;
        }
        //socialTopic.reply();
        SocialTopicComment socialTopicComment = SocialTopicComment.builder()
                .topicId(cmd.getTopicId())
                .content(cmd.getContent())
                .userId(UserIdUtils.getUserId())
                .build();
        socialTopicComment.init();
        socialTopicCommentRepo.create(socialTopicComment);
    }


    /**
     * 回复评论
     *
     * @param cmd
     */
    public SocialTopicReply replytopic(SocialTopicReplyCmd cmd) {
        log.info(" -- 回复评论输入参数，{}", cmd);
        SocialTopicReply socialTopicReply;
        if (0 == cmd.getReplyType()) {
            SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(cmd.getReplyId());
            socialTopicReply = SocialTopicReply.builder()
                    .replyId(cmd.getReplyId())
                    .content(cmd.getContent())
                    .replyType(cmd.getReplyType())
                    .userId(UserIdUtils.getUserId())
                    .toUserId(socialTopicComment.getUserId())
                    .commentId(socialTopicComment.getSocialTopicCommentId().getId())
                    .build();
        } else {
            SocialTopicReply temp = socialTopicReplyRepo.findById(cmd.getReplyId());
            socialTopicReply = SocialTopicReply.builder()
                    .replyId(cmd.getReplyId())
                    .content(cmd.getContent())
                    .replyType(cmd.getReplyType())
                    .userId(UserIdUtils.getUserId())
                    .toUserId(temp.getUserId())
                    .commentId(temp.getCommentId())
                    .build();
        }
        socialTopicReply.init();
        socialTopicReplyRepo.create(socialTopicReply);
        return socialTopicReply;
    }

    /**
     * 点赞
     *
     * @param cmd 点赞类型 0点赞帖子 1点赞评论
     */
    public int uptopic(TopicOperateCmd cmd) {
        log.info(" -- 点赞，输入参数，{}", cmd);
        SocialTopicUp socialTopicUp = socialTopicUpRepo.findByType(UserIdUtils.getUserId(), cmd.getAimsId());
        if (null != socialTopicUp) {
            socialTopicUpRepo.deleteById(socialTopicUp.getSocialTopicUpId().getId());
            // 0点赞帖子 1点赞评论
            if (0 == cmd.getOperateType()) {
                SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
                socialTopic.down();
            } else {
                SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(cmd.getAimsId());
                socialTopicComment.down();
            }
            return -1;
        }
        socialTopicUp = SocialTopicUp.builder()
                .operateType(cmd.getOperateType())
                .topicId(cmd.getAimsId())
                .userId(UserIdUtils.getUserId())
                .build();
        socialTopicUp.init();
        socialTopicUpRepo.create(socialTopicUp);
        if (0 == cmd.getOperateType()) {
            SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
            if (null != socialTopic) {
                socialTopic.up();
            }
        } else {
            SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(cmd.getAimsId());
            if (null != socialTopicComment) {
                socialTopicComment.up();
            }
        }
        return 1;
    }


    /**
     * 收藏帖子
     *
     * @param cmd
     */
    public int collecttopic(TopicOperateCmd cmd) {
        log.info(" -- 收藏帖子，输入参数，{}", cmd);
        SocialTopicCollect socialTopicCollect = socialTopicCollectRepo.findByType(UserIdUtils.getUserId(), cmd.getAimsId());
        if (null != socialTopicCollect) {
            socialTopicCollectRepo.deleteById(socialTopicCollect.getSocialTopicCollectId().getId());
            SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
            socialTopic.cacelCollect();
            return -1;
        }
        SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
        socialTopic.collect();
        socialTopicCollect = SocialTopicCollect.builder()
                .collectTime(new Date())
                .topicId(cmd.getAimsId())
                .userId(UserIdUtils.getUserId())
                .build();
        socialTopicCollect.init();
        socialTopicCollectRepo.create(socialTopicCollect);
        return 1;
    }

    /**
     * 审核帖子
     *
     * @param cmd
     */
    public void reviewTopic(SocialReviewCmd cmd) {
        log.info(" -- 审核帖子，输入参数，{}", cmd);
        if (0 == cmd.getOperateType()) {
            SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
            socialTopic.review(cmd.getAuditStatus());
        } else if (1 == cmd.getOperateType()) {
            SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(cmd.getAimsId());
            socialTopicComment.review(cmd.getAuditStatus());
            SocialTopic socialTopic = socialTopicRepo.findById(socialTopicComment.getTopicId());
            socialTopic.reply();
        } else {
            SocialTopicReply socialTopicReply = socialTopicReplyRepo.findById(cmd.getAimsId());
            socialTopicReply.review(cmd.getAuditStatus());
        }
    }

    /**
     * 保存资源
     *
     * @param socialTopic
     */
    public void saveAlbum(SocialTopic socialTopic, List<String> urls) {
        if (null == urls || urls.size() < 1) {
            return;
        }
        SocialTopicAlbum socialTopicAlbum = null;
        int i = urls.size();
        for (String url : urls) {
            socialTopicAlbum = SocialTopicAlbum.builder()
                    .topicId(socialTopic.getSocialTopicId().getId())
                    .sort(i)
                    .photoUrl(url)
                    .build();
            i--;
            socialTopicAlbum.init();
            socialTopicAlbumRepo.create(socialTopicAlbum);
        }
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    public int deleteComment(Long id) {
        log.info(" -- 删除评论,输入参数{}", id);
        SocialTopicComment socialTopicComment = socialTopicCommentRepo.findById(id);
        if (socialTopicComment.getUserId().longValue() != UserIdUtils.getUserId().longValue()) {
            return 0;
        }
        if (0 == socialTopicComment.getAuditStatus()) {
            SocialTopic socialTopic = socialTopicRepo.findById(socialTopicComment.getTopicId());
            socialTopic.unReply();
        }
        return socialTopicCommentRepo.deleteById(id);
    }


    public int recommendTopic(SocialRecommendCmd cmd) {
        log.info(" -- 将帖子设置为推荐,输入参数{}", cmd);
        SocialTopic socialTopic = socialTopicRepo.findById(cmd.getAimsId());
        if (null != socialTopic) {
            if (null != socialTopic.getAttr() && 1 == socialTopic.getAttr()) {
                socialTopic.setAttr(0);
            } else {
                socialTopic.setAttr(1);
            }
            return socialTopicRepo.recommendTopic(socialTopic);
        }
        return 0;
    }


    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    public int deleteTopic(Long id) {
        log.info(" -- 删除评论,输入参数{}", id);
        return socialTopicRepo.deleteById(id);
    }


    /**
     * 屏蔽
     *
     * @param cmd
     * @return
     */
    public int shieldTopic(SocialTopicShieldCmd cmd) {
        log.info(" -- 屏蔽,输入参数{}", cmd);
        TopicShield topicShield = TopicShield.builder()
                .shieldType(cmd.getShieldType())
                .targetId(cmd.getTargetId())
                .userId(UserIdUtils.getUserId())
                .build();
        topicShield.init();
        return socialTopicShieldRepo.create(topicShield);
    }

    /**
     * 取消屏蔽
     *
     * @param id
     * @return
     */
    public int cancelShield(Long id) {
        log.info(" -- 屏蔽,输入参数{}", id);
        return socialTopicShieldRepo.deleteById(id);
    }

}
