package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * Created by 19458 on 2020/1/3.
 */
@Data
public class NoticeUserDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 标题
     */
    private String title;
    /**
     * 公告附件
     */
    private String attachment;
    /**
     * 是否首页展示  0展示  1不展示
     */
    private Integer isShow;
    /**
     * 公告正文
     */
    private String content;
    /**
     * 0、关闭；1、开启
     */
    private Integer noticeStatus;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 有效期开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 有效期结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
