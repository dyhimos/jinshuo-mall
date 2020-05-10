package com.jinshuo.mall.service.social.service.com;

import com.jinshuo.mall.domain.social.social.SocialCircle;
import com.jinshuo.mall.service.social.application.cmd.SocialCircleCmd;
import com.jinshuo.mall.service.social.mybatis.SocialCircleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/12/25.
 */
@Slf4j
@Service
public class SocialCircleComService {

    @Autowired
    private SocialCircleRepo socialCircleRepo;

    public int save(SocialCircleCmd cmd) {
        SocialCircle socialCircle = SocialCircle.builder()
                .name(cmd.getName())
                .remark(cmd.getRemark())
                .weight(cmd.getWeight())
                .build();
        socialCircle.init();
        return socialCircleRepo.create(socialCircle);
    }
}
