package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.cpmplain.Complain;
import com.jinshuo.mall.domain.user.model.cpmplain.ComplainId;
import com.jinshuo.mall.service.user.application.cmd.ComplainCmd;
import com.jinshuo.mall.service.user.mybatis.ComplainRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComplainCmdService {

    @Autowired
    private ComplainRepo complainRepo;

    /**
     * 保存/更新app版本管理
     *
     * @param cmd
     */
    public void save(ComplainCmd cmd) {
        Long userId = null;
        try {
            userId = UserIdUtils.getUserId();
        } catch (IcBizException e) {
            log.error(e.getMessage(), e.getCode());
        }

        Complain complain = Complain.builder()
                /** 举报类型*/
                .type(cmd.getType())
                /** 举报详情*/
                .detail(cmd.getDetail())
                /** 帖子id*/
                .topicId(cmd.getTopicId())
                /** 用户id*/
                .userId(userId)
                .imagePaths(cmd.getImagePaths())
                .build();
        if (cmd.getId() != null) {
            Long complainId = cmd.getId();
            //查询是否存在
            Complain isExit = complainRepo.findById(complainId);
            if (isExit == null) {
                throw new UcBizException(UcReturnCode.UC200045.getMsg(), UcReturnCode.UC200045.getCode());
            }
            complain.setComplainId(new ComplainId(complainId));

        } else {
            complain.preInsert();
        }
        complainRepo.save(complain);
    }

    /**
     * 删除app版本管理
     *
     * @param cmd
     */
    public void delete(ComplainCmd cmd) {
        Long complainId = cmd.getId();
        //查询是否存在
        Complain complain = complainRepo.findById(complainId);
        if (complain == null) {
            throw new UcBizException(UcReturnCode.UC200045.getMsg(), UcReturnCode.UC200045.getCode());
        }
        complain.setStatus(Status.DELETE);
        complainRepo.save(complain);
    }
}

	
