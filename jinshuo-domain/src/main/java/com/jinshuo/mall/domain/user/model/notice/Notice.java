package com.jinshuo.mall.domain.user.model.notice;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/26.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends IdentifiedEntity {
    private NoticeId noticeId;
    private String title;
    private String content;
    private Integer isShow;
    private Integer sort;
    private String url;
    private Integer noticeStatus;
    private Date startTime;
    private Date endTime;
    private String attachment;

    public void init() {
        this.noticeId = new NoticeId(CommonSelfIdGenerator.generateId());
        super.preInsert();
    }

    public void update() {

    }
}
