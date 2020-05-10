package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.social.SocialCircleUser;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialCircleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialCircleUserRepo {

    @Autowired
    private SocialCircleUserMapper socialCircleUserMapper;

    public int create(SocialCircleUser socialCircleUser) {
        return socialCircleUserMapper.create(socialCircleUser);
    }

    public SocialCircleUser findById(Long id) {
        return socialCircleUserMapper.queryById(id);
    }

    public int deleteById(Long id) {
        return socialCircleUserMapper.deleteById(id);
    }
}