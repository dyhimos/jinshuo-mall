package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.collect.Collect;
import com.jinshuo.mall.service.user.application.cmd.CollectCmd;
import com.jinshuo.mall.service.user.mybatis.CollectRepo;
import com.jinshuo.mall.service.user.service.query.CollectQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class CollectComService {

    @Autowired
    private CollectRepo collectRepo;

    @Autowired
    private CollectQueryService collectQueryService;

    /**
     * 新增标签
     *
     * @param cmd
     */
    public int create(CollectCmd cmd) {
        Collect collect = new Collect();
        BeanUtils.copyProperties(cmd, collect);
        collect.insert();
        return collectRepo.save(collect);
    }

    public int delete(Long id) {
        return collectRepo.delete(UserIdUtils.getUserId(),id);
    }

    public int checkIsCollect(CollectCmd cmd) {
        List<Collect> collects = collectRepo.queryByTargetId(UserIdUtils.getUserId(), cmd.getTargetId());
        if (null != collects && collects.size() > 0) {
            return 0;
        }
        return 1;
    }
}
