package com.jinshuo.mall.domain.user.model.userAccount;


/**
 * @Classname IsUserAccountRepo
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 * @author dongyh
 */
public interface IsUserAccountRepo {

    UserAccountId nextId();

    /**
     * 保存用户账户
     * @param userAccount
     */
    void save(UserAccount userAccount);
}
