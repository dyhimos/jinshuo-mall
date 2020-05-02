package com.jinshuo.mall.domain.user.model.member;

/**
 * @author dongyh
 * @Classname IsMemberRepo
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 */
public interface IsMemberRepo {

    MemberId nextId();

    /**
     * 保存用户账户
     *
     * @param member
     */
    void save(Member member);
}
