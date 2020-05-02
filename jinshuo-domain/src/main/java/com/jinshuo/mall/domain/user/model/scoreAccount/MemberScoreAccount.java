package com.jinshuo.mall.domain.user.model.scoreAccount;

import com.jinshuo.mall.domain.user.model.member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyh
 * @Classname MemberScoreAccount
 * @Description 会员积分账户
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberScoreAccount {

    /**
     * 会员积分账户id
     */
    private MemberScoreAccountId memberScoreAccountId;

    /**
     * 账户ID
     */
    private MemberId memberId;

    /**
     * 总积分
     */
    private Integer totalScore;

    /**
     * 消耗积分
     */
    private Integer expendScore;

    /**
     * 可用积分
     */
    private Integer useableScore;
}
