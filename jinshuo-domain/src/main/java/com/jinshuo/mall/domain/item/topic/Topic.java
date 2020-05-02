package com.jinshuo.mall.domain.item.topic;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by dongyh on 2019/9/29.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends IdentifiedEntity {
    private TopicId topicId;
    private String code;
    private String name;
    private Integer type;
    private Integer sort;
    private Integer showType;
    private Long pid;
    private Date startTime;
    private Date endTime;
    private Integer topicStatus;
    private String topicDesc;
    private String mainPicture;
    private String signPicture;
    private String posterPicture;
    private String color;
    private Long shopId;
    private Integer headingShowFlag; // 0文字 1：图标
    private String headingColor; // 字体颜色

    public Topic insert() {
        this.topicId = new TopicId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        return this;
    }
}
