package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/9/29.
 */
@Data
public class TopicAddProductCmd {
    private Long topicId;
    private List<TopicProductCmd> list;
}
