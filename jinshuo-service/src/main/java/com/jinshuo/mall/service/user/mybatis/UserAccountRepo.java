package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.service.user.application.dto.UserManagerAccountDto;
import com.jinshuo.mall.service.user.application.qry.UserAccountQry;
import com.jinshuo.mall.service.user.mybatis.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname GoodsOrderRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class UserAccountRepo {

    @Autowired(required = false)
    private UserAccountMapper mapper;

    public UserAccountId nextId() {
        return new UserAccountId(CommonSelfIdGenerator.generateId());
    }

    public void save(UserAccount userAccount) {
        mapper.save(userAccount);
    }

    public UserAccount findById(UserAccountId userAccountId) {
        return mapper.findById(userAccountId);
    }

    public UserAccount findByUserName(String username) {
        return mapper.findByUserName(username);
    }

    public UserAccount findByPhone(String phone) {
        return mapper.findByPhone(phone);
    }


    /**
     * 根据手机号码和用户类型查询用户
     *
     * @param phone
     * @param type
     * @return
     */
    public UserAccount findByPhoneAndType(String phone, Integer type) {
        return mapper.findByPhoneAndType(phone, type);
    }

    public UserAccount findByInvaildCode(String invaildCode) {
        return mapper.findByInvaildCode(invaildCode);
    }

    public int updatePassword(UserAccount userAccount) {
        return mapper.updatePassword(userAccount);
    }

    public int update(UserAccount userAccount) {
        return mapper.update(userAccount);
    }

    public List<UserAccount> findByUserNameAndType(String username, Integer type) {
        return mapper.queryByUserNameAndType(username, type);
    }

    public int updateLoginLog(UserAccount userAccount) {
        return mapper.updateLoginLog(userAccount);
    }


    /**
     * 查询管理员列表
     *
     * @param query
     * @return
     */
    public List<UserManagerAccountDto> queryManagerAccount(UserAccountQry query) {
        return mapper.queryManagerAccount(query);
    }

    /**
     * 删除用户
     *
     * @param userAccountId
     */
    public void deleteUserAccount(UserAccountId userAccountId) {
        mapper.deleteUserAccount(userAccountId.getId());
    }

}
