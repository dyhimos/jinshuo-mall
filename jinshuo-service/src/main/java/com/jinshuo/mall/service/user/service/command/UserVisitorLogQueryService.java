package com.jinshuo.mall.service.user.service.command;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.service.user.application.dto.UserVisitorLogDto;
import com.jinshuo.mall.service.user.application.qry.UserVisitorLogQry;
import com.jinshuo.mall.service.user.mybatis.VisitorLogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserVisitorLogQueryService {

	@Autowired
	private VisitorLogRepo userVisitorLogRepo;

	 /**
     * 查询列表
     * @param query
     */
    public PageInfo<UserVisitorLogDto> queryList(UserVisitorLogQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<UserVisitorLogDto> userVisitorLogList = userVisitorLogRepo.findAll(query);
        PageInfo pageInfo = new PageInfo<>(userVisitorLogList);
        return pageInfo;
    }
}

	
