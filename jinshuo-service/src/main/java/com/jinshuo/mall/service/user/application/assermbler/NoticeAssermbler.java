package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.notice.Notice;
import com.jinshuo.mall.service.user.application.dto.NoticeUserDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2020/1/3.
 */
public class NoticeAssermbler {

    /**
     * @param notice
     * @return
     */
    public static NoticeUserDto assembleDto(Notice notice) {
        if (null == notice) {
            return null;
        }
        NoticeUserDto dto = new NoticeUserDto();
        BeanUtils.copyProperties(notice, dto);
        dto.setId(notice.getNoticeId().getId());
        return dto;
    }



}
