package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.member.IsMemberRepo;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.member.MemberId;
import com.jinshuo.mall.service.user.application.qry.DisSalemanQry;
import com.jinshuo.mall.service.user.application.qry.MemberQry;
import com.jinshuo.mall.service.user.mybatis.mapper.MemberMapper;
import com.jinshuo.mall.service.user.mybatis.mapper.ScoreAccountMapper;
import com.jinshuo.mall.service.user.mybatis.mapper.ScoreRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname MemberRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class MemberRepo implements IsMemberRepo {


    @Autowired(required = false)
    private MemberMapper mapper;

    @Autowired(required = false)
    private ScoreAccountMapper scoreAccountMapper;

    @Autowired(required = false)
    private ScoreRecordMapper scoreRecordMapper;

    @Override
    public MemberId nextId() {
        return new MemberId(CommonSelfIdGenerator.generateId());
    }

    @Override
    public void save(Member member) {
        mapper.save(member);
    }

    /**
     * 查询会员列表
     *
     * @param query
     * @return
     */
    public List<Member> queryMemberList(MemberQry query) {
        return mapper.queryMemberList(query);
    }


    public Member queryByUserId(Long userId) {
        return mapper.queryByUserId(userId);
    }


    public Member findById(Long id) {
        return mapper.findById(id);
    }

    public void updateById(Member member) {
        mapper.updateById(member);

        //更新父节点
        if (member.getParentMember() != null) {
            mapper.updateById(member.getParentMember());
        }
    }

    /**
     * 订单消费，更新积分信息，添加积分记录
     *
     * @param member
     */
    @Transactional
    public void updateCost(Member member) {
        try {
            mapper.updateById(member);
            //更新父节点
            if (member.getParentMember() != null) {
                mapper.updateById(member.getParentMember());
            }

            //积分信息
            scoreAccountMapper.update(member.getMemberScoreAccount());
            //积分记录
            scoreRecordMapper.save(member.getMemberScoreRecord());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id列表查询会员列表
     *
     * @return
     */
    public List<Member> getByIds(List<Long> memIds) {
        return mapper.getByIds(memIds);
    }


    /**
     * 查询从该用户分享的二维码进入的人数
     *
     * @param userId
     * @return
     */
    public Integer getFromUser(Long userId) {
        return mapper.getFromUser(userId);
    }


    /**
     * 查询下面有多少麦客
     *
     * @param userId
     * @return
     */
    public Integer getFromUserAndDis(Long userId) {
        return mapper.getFromUser(userId);
    }

    /**
     * 查询我下面有多少会员
     *
     * @param memId
     * @return
     */
    public List<Member> queryAllMyCustomer(Long memId) {
        return mapper.queryAllMyCustomer(memId);
    }


    /**
     * 查询我名下有多少客户
     *
     * @return
     */
    public List<Member> getMyCustomer(Long memId) {
        return mapper.queryMyCustomer(memId);
    }

    /**
     * 查询我名下有多少分销员
     *
     * @return
     */
    public List<Member> getMySaleman(Long memId) {
        return mapper.queryMySaleman(memId);
    }

    /**
     * 查询我的上级且必须是分销员
     *
     * @param userId
     * @return
     */
    public Member getMyUpperSaleman(Long userId) {
        return mapper.queryMyUpperSaleman(userId);
    }

    public List<Member> findMySubordinateInfo(DisSalemanQry qry) {
        return mapper.queryMySubordinateInfo(qry);
    }

    /**
     * 更新粉丝状态为关注状态
     *
     * @param notSubscribeList
     * @param shopId
     */
    public void subscribeFans(List<String> notSubscribeList, Long shopId) {
        mapper.subscribeFans(notSubscribeList, shopId);
    }


    /**
     * 更新粉丝关注状态
     *
     * @param notSubscribeList
     * @param shopId
     */
    public void unSubscribeFans(List<String> notSubscribeList, Long shopId) {
        mapper.unSubscribeFans(notSubscribeList, shopId);
    }

    public int comInfo(Member member) {
        return mapper.comInfo(member);
    }

    public int updatePhone(Member member) {
        return mapper.updatePhone(member);
    }

    ;


    public int updatePayPassword(Member member) {
        return mapper.updatePayPassword(member);
    }

    ;
}
