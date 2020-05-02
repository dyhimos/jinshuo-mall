package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.notice.Notice;
import com.jinshuo.mall.service.user.mybatis.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Repository
public class NoticeRepo {

    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    public int save(Notice notice) {
        return noticeMapper.create(notice);
    }

    public Notice queryById(Long id) {
        return noticeMapper.queryById(id);
    }

    public int delete(Long id) {
        return noticeMapper.delete(id);
    }

    public List<Notice> queryNotice(Notice qry) {
        return noticeMapper.queryNotice(qry);
    }

    public List<Notice> queryMyNotice(Notice qry) {
        return noticeMapper.queryMyNotice(qry);
    }

    public int update(Notice notice) {
        return noticeMapper.update(notice);
    }
}
