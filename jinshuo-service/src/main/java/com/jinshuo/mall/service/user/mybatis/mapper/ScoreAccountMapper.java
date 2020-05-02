package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.member.MemberId;
import com.jinshuo.mall.domain.user.model.scoreAccount.MemberScoreAccount;
import org.apache.ibatis.annotations.*;


/**
 * 积分账户
 *
 * @Classname ScoreAccountMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface ScoreAccountMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO member_score_account(" +
            "id,mem_id,total_score,expend_score,useable_score) " +
            "VALUES(" +
            "#{memberScoreAccountId.id},#{memberId.id},#{totalScore},#{expendScore},#{useableScore})")
    int save(MemberScoreAccount memberScoreAccount);

    /**
     * 查询所有的积分用户
     *
     * @return
     */
    @Results(
            id = "memberScoreAccount",
            value = {
                    @Result(property = "memberScoreAccountId.id", column = "id"),
                    @Result(property = "memberId.id", column = "mem_id"),
                    @Result(property = "totalScore", column = "total_score"),
                    @Result(property = "expendScore", column = "expend_score"),
                    @Result(property = "useableScore", column = "useable_score"),
            }
    )
    @Select("SELECT * FROM member_score_account")
    MemberScoreAccount queryDefault();

    /**
     * 查询账户信息
     *
     * @param memberId
     * @return
     */
    @ResultMap(value = "memberScoreAccount")
    @Select("SELECT * FROM member_score_account where mem_id=#{id}")
    MemberScoreAccount queryByMemId(MemberId memberId);

    /**
     * 更新积分
     *
     * @param memberScoreAccount
     * @return
     */
    @Update("UPDATE  member_score_account SET total_score=#{totalScore} ," +
            "expend_score=#{expendScore},useable_score=#{useableScore}" +
            " WHERE mem_id = #{memberId.id}")
    int update(MemberScoreAccount memberScoreAccount);
}
