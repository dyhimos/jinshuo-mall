package com.jinshuo.mall.service.social.application.assermbler;

import com.jinshuo.mall.domain.social.topic.SocialTopic;
import com.jinshuo.mall.domain.social.topic.SocialTopicComment;
import com.jinshuo.mall.domain.social.topic.SocialTopicUp;
import com.jinshuo.mall.service.social.application.dto.SocialTopicCommentDto;
import com.jinshuo.mall.service.social.application.dto.SocialTopicDto;
import com.jinshuo.mall.service.social.application.dto.SocialTopicUpDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class SocialTopicAssembler {

    /**
     * @param socialTopic
     * @return
     */
    public static SocialTopicDto assembleTopicDto(SocialTopic socialTopic) {
        if (null == socialTopic) {
            return null;
        }
        SocialTopicDto dto = new SocialTopicDto();
        BeanUtils.copyProperties(socialTopic, dto);
        dto.setId(socialTopic.getSocialTopicId().getId());
        return dto;
    }

    /**
     * @param socialTopicComment
     * @return
     */
    public static SocialTopicCommentDto assembleCommentDto(SocialTopicComment socialTopicComment) {
        if (null == socialTopicComment) {
            return null;
        }
        SocialTopicCommentDto dto = new SocialTopicCommentDto();
        BeanUtils.copyProperties(socialTopicComment, dto);
        dto.setId(socialTopicComment.getSocialTopicCommentId().getId());
        return dto;
    }


    /**
     * @param socialTopicUp
     * @return
     */
    public static SocialTopicUpDto assembleUpDto(SocialTopicUp socialTopicUp) {
        if (null == socialTopicUp) {
            return null;
        }
        SocialTopicUpDto dto = new SocialTopicUpDto();
        BeanUtils.copyProperties(socialTopicUp, dto);
        dto.setId(socialTopicUp.getSocialTopicUpId().getId());
        return dto;
    }


}
