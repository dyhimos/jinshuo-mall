package com.jinshuo.mall.service.social.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String contents;
    /**
     * 话题属性
     */
    private Integer attr;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 权重
     */
    private Integer weight;

    /**
     * 圈子编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long circleId;
    /**
     * 回复数
     */
    private Integer replyCount;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 点赞数
     */
    private Integer upCount;
    /**
     * 收藏数
     */
    private Integer collectCount;
    /**
     * 发帖时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topicTime;
    /**
     * 最后修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;

    private List<String> urls;

    /**
     * userId
     */
    private Integer userType;

    /**
     * userId
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否点赞 0是 1否
     */
    private Integer isUp;
    /**
     * 是否收藏 0是 1否
     */
    private Integer isCollect;
}