package com.jinshuo.mall.service.social.mybatis;

import com.jinshuo.mall.domain.social.social.SocialCircle;
import com.jinshuo.mall.service.social.mybatis.mapper.SocialCircleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 19458 on 2019/12/25.
 * @author dyh
 */
@Repository
public class SocialCircleRepo {

    @Autowired
    private SocialCircleMapper socialCircleMapper;

    public int create(SocialCircle socialCircle) {
        return socialCircleMapper.create(socialCircle);
    }

    public SocialCircle findById(Long id) {
        return socialCircleMapper.queryById(id);
    }

    public int deleteById(Long id) {
        return socialCircleMapper.deleteById(id);
    }
}
