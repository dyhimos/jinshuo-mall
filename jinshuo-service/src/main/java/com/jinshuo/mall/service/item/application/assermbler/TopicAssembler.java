package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.topic.Topic;
import com.jinshuo.mall.service.item.application.dto.TopicDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class TopicAssembler {

    /**
     * @param topic
     * @return
     */
    public static TopicDto assembleDto(Topic topic) {
        if (null == topic) {
            return null;
        }
        TopicDto dto = new TopicDto();
        BeanUtils.copyProperties(topic, dto);
        dto.setId(topic.getTopicId().getId().toString());
        if (null != topic.getPid()) {
            dto.setPid(topic.getPid().toString());
        }
        return dto;
    }
}
