package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.IsUserAccountPlatformRepo;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatformId;
import com.jinshuo.mall.service.user.mybatis.mapper.MemberMapper;
import com.jinshuo.mall.service.user.mybatis.mapper.UserAccountMapper;
import com.jinshuo.mall.service.user.mybatis.mapper.UserAccountPlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname GoodsOrderRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class UserAccountPlatformRepo implements IsUserAccountPlatformRepo {


    @Autowired(required = false)
    private UserAccountPlatformMapper mapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 会员积分账户信息
     */
    @Autowired
    private ScoreAccountRepo scoreAccountRepo;

    /**
     * 获取id
     *
     * @return
     */
    @Override
    public UserAccountPlatformId nextId() {
        return new UserAccountPlatformId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存微信账户信息
     *
     * @param userAccountPlatform 微信账户
     */
    @Override
    @Transactional
    public void save(UserAccountPlatform userAccountPlatform) {
        //保存微信信息
        userAccountPlatform.preInsert();
        mapper.save(userAccountPlatform);

        //保存账户信息
        if (userAccountPlatform.getUserAccount() != null) {
            userAccountMapper.save(userAccountPlatform.getUserAccount());
        }

        //保存会员信息
        if (userAccountPlatform.getMember() != null) {
            memberMapper.save(userAccountPlatform.getMember());
            //如果有父则更新父的东西
            if (userAccountPlatform.getMember().getParentMember() != null) {
                memberMapper.updateById(userAccountPlatform.getMember().getParentMember());
            }
            //会员积分账户信息
            scoreAccountRepo.save(userAccountPlatform.getMember().getMemberScoreAccount());
        }
    }

    /**
     * 更新微信信息
     *
     * @param userAccountPlatform
     */
    @Transactional
    public void update(UserAccountPlatform userAccountPlatform) {
        //更新微信信息
        mapper.update(userAccountPlatform);

        //更新会员信息
        if (userAccountPlatform.getMember() != null) {
            memberMapper.update(userAccountPlatform.getMember());
        }
    }

    /**
     * 根据id查询微信账户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserAccountPlatform findById(String id) {
        return mapper.findById(id);
    }


    /**
     * 根据openid查询用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UserAccountPlatform findByOpenid(String openid, Long shopId) {
        return mapper.findByOpenid(openid, shopId);
    }


    /**
     * 根据用户id查询用户信息
     *
     * @param userAccountId
     * @return
     */
    public UserAccountPlatform findByUserId(UserAccountId userAccountId) {
        return mapper.findByUserId(userAccountId);
    }


    /**
     * 查询登录方式的是否存在数据
     *
     * @param unionId 统一账户id
     * @return
     */
    @Override
    public List<UserAccountPlatform> findByUnionId(String unionId, Long shopId) {
        return mapper.findByUnionId(unionId, shopId);
    }

    /**
     * 获取店铺已存在的openid
     *
     * @return
     */
    public List<String> getExistOpenIds(Long shopId) {
        return mapper.getExistOpenIds(shopId);
    }
}
