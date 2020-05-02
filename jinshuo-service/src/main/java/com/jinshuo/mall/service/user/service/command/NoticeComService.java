package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.mall.domain.user.model.notice.Notice;
import com.jinshuo.mall.service.user.application.cmd.NoticeCmd;
import com.jinshuo.mall.service.user.mybatis.NoticeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2020/1/3.
 */
@Slf4j
@Service
public class NoticeComService {

    @Autowired
    private NoticeRepo noticeRepo;


    /**
     * 保存通知
     *
     * @param cmd
     * @return
     */
    public int save(NoticeCmd cmd) {
        log.info(" -- 保存通知，输入参数{}", cmd);
        if (null != cmd.getId()) {
            return update(cmd);
        }
        Notice notice = Notice.builder()
                .url(cmd.getUrl())
                .attachment(cmd.getAttachment())
                .content(cmd.getContent())
                .isShow(cmd.getIsShow())
                .title(cmd.getTitle())
                .noticeStatus(0)
                .sort(cmd.getSort())
                .startTime(cmd.getStartTime())
                .endTime(cmd.getEndTime())
                .build();
        notice.init();
        return noticeRepo.save(notice);
    }

    /**
     * 删除通知公告
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return noticeRepo.delete(id);
    }

    /**
     * 修改通知公告信息
     *
     * @param cmd
     * @return
     */
    public int update(NoticeCmd cmd) {
        Notice notice = noticeRepo.queryById(cmd.getId());
        if (null == notice) {
            return 0;
        }
        BeanUtils.copyProperties(cmd, notice);
        notice.update();
        return noticeRepo.update(notice);
    }
}
