package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.scoreRecord.MemberScoreRecord;
import com.jinshuo.mall.domain.user.model.scoreRecord.MemberScoreRecordId;
import com.jinshuo.mall.service.user.mybatis.mapper.ScoreRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分流水
 *
 * @Classname ScoreRecordRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class ScoreRecordRepo {

    @Autowired(required = false)
    private ScoreRecordMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public MemberScoreRecordId nextId() {
        return new MemberScoreRecordId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存
     *
     * @param memberScoreRecord
     */
    public void save(MemberScoreRecord memberScoreRecord) {
        mapper.save(memberScoreRecord);
    }

    /**
     * 根据id条件查询订单
     *
     * @param memberScoreRecord
     * @return
     */
    public List<MemberScoreRecord> findMemberScoreRecordList(MemberScoreRecord memberScoreRecord) {
        List<MemberScoreRecord> memberScoreRecordList = mapper.findMemberScoreRecordList(memberScoreRecord);
        return memberScoreRecordList;
    }
}
