package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.member.MemberId;
import com.jinshuo.mall.domain.user.model.scoreRecord.MemberScoreRecord;
import com.jinshuo.mall.domain.user.model.scoreRecord.ScoreTypeEnums;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 积分记录
 * @Classname ScoreRecordMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface ScoreRecordMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO member_score_record(" +
            "id,mem_id,type,score,expend_scosource_type,source_memo) " +
            "VALUES(" +
            "#{memberScoreRecordId.id},#{memberId.id},#{type.value},#{score},#{expendScosourceType},#{sourceMemo})")
    int save(MemberScoreRecord memberScoreRecord);


    /**
     * 查询消费记录
     * @param memberScoreRecord
     * @return
     */
    @Results(
            id = "memberScoreRecordResult",
            value = {
                    @Result(property = "memberScoreRecordId.id", column = "id"),
                    @Result(property = "memberId.id", column = "mem_id"),
                    @Result(property = "type", column = "type",javaType = ScoreTypeEnums.class,typeHandler = UniversalEnumHandler.class),
                    @Result(property = "score", column = "score"),
                    @Result(property = "expendScosourceType", column = "expend_scosource_type"),
                    @Result(property = "sourceMemo", column = "source_memo")
            }
    )
    @SelectProvider(type = DynamicSql.class, method = "queryScoreRecordSql")
    List<MemberScoreRecord> findMemberScoreRecordList(MemberScoreRecord memberScoreRecord);


    /**
     * 查询个人的积分记录
     * @param memberId
     * @return
     */
    @ResultMap(value="memberScoreRecordResult")
    @Select("SELECT * FROM member_score_record WHERE mem_id=#{id}")
    List<MemberScoreRecord> findScoreRecordListByMemId(MemberId memberId);

}
