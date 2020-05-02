package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.user.model.notice.Notice;
import com.jinshuo.mall.service.user.application.assermbler.NoticeAssermbler;
import com.jinshuo.mall.service.user.application.dto.NoticeUserDto;
import com.jinshuo.mall.service.user.application.qry.NoticeQry;
import com.jinshuo.mall.service.user.mybatis.NoticeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2020/1/3.
 */
@Slf4j
@Service
public class NoticeQueryService {

    @Autowired
    private NoticeRepo noticeRepo;


    /**
     * 获取我的通知
     *
     * @return
     */
    public NoticeUserDto getNoticeDetal(Long id) {
        NoticeUserDto dto = NoticeAssermbler.assembleDto(noticeRepo.queryById(id));
        return dto;
    }


    /**
     * 获取通知
     *
     * @return
     */
    public PageInfo getNoticePage(NoticeQry qry) {
        log.info(" -- 后台分页查询通知");
        List<Notice> noticeList = noticeRepo.queryNotice(null);
        PageInfo pageInfo = new PageInfo(noticeList);
        List<NoticeUserDto> dtos = noticeList.stream().map(notice -> NoticeAssermbler.assembleDto(notice)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 获取我的通知
     *
     * @return
     */
    public PageInfo getMyNotice(NoticeQry qry) {
        log.info(" -- 前台分页查询通知");
        Notice temp = Notice.builder().isShow(qry.getIsShow()).build();
        List<Notice> noticeList = noticeRepo.queryMyNotice(temp);
        PageInfo pageInfo = new PageInfo(noticeList);
        List<NoticeUserDto> dtos = noticeList.stream().map(notice -> NoticeAssermbler.assembleDto(notice)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

}
