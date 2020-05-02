package com.jinshuo.mall.domain.user.model.scoreRecord;

import com.jinshuo.mall.domain.user.model.member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyh
 * @Classname Member
 * @Description 会员积分流水
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberScoreRecord {

    /**
     * 会员信息ID
     */
    private MemberScoreRecordId memberScoreRecordId;

    /**
     * 记录类型1、增加；2、消费
     */
    private ScoreTypeEnums type;

    /**
     * 会员编号
     */
    private MemberId memberId;

    /**
     * 积分值
     */
    private Integer score;

    /**
     * 积分来源（1：商品购买）
     */
    private int expendScosourceType;

    /**
     * 来源详情
     */
    private String sourceMemo;
}
