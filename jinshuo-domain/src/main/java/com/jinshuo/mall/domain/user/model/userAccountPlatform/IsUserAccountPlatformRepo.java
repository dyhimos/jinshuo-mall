package com.jinshuo.mall.domain.user.model.userAccountPlatform;

import java.util.List;

/**
 * @Classname IsUserAccountPlatformRepo
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 * @author dongyh
 */
public interface IsUserAccountPlatformRepo {

    UserAccountPlatformId nextId();

    /**
     * 保存微信账户
     * @param userAccountPlatform 微信账户
     */
    void save(UserAccountPlatform userAccountPlatform);

    /**
     *根据id查询头像信息
     * @param id
     * @return
     */
    UserAccountPlatform findById(String id);


    /**
     * 根据用户openid查询信息
     * @param openid
     * @return
     */
    UserAccountPlatform findByOpenid(String openid, Long shopId);


    /**
     * 根据unionId查询用户信息
     * @param unionId 统一账户id
     * @return
     */
    List<UserAccountPlatform> findByUnionId(String unionId, Long shopId);

}
