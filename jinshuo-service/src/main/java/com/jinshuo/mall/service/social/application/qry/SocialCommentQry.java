package com.jinshuo.mall.service.social.application.qry;

import lombok.Data;

/**
 * Created by 19458 on 2019/12/26.
 */
@Data
public class SocialCommentQry extends PageQry{
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;
    /**
     * 审核人
     */
    private Long auditUser;

}
