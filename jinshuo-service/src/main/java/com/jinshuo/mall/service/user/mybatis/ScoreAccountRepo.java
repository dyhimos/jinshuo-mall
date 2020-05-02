package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.scoreAccount.MemberScoreAccount;
import com.jinshuo.mall.domain.user.model.scoreAccount.MemberScoreAccountId;
import com.jinshuo.mall.service.user.mybatis.mapper.ScoreAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 积分账户
 *
 * @Classname ScoreAccountRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class ScoreAccountRepo {

    @Autowired(required = false)
    private ScoreAccountMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public MemberScoreAccountId nextId() {
        return new MemberScoreAccountId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存
     *
     * @param memberScoreAccount
     */
    public void save(MemberScoreAccount memberScoreAccount) {
        mapper.save(memberScoreAccount);
    }
}
