package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.cpmplain.Complain;
import com.jinshuo.mall.service.user.application.assermbler.ComplainAssermbler;
import com.jinshuo.mall.service.user.application.dto.ComplainDto;
import com.jinshuo.mall.service.user.application.qry.ComplainQry;
import com.jinshuo.mall.service.user.mybatis.ComplainRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ComplainQueryService {

	@Autowired
	private ComplainRepo complainRepo;

    /**
     * 查询单条记录详情
     * @param id
     * @return
     */
    public ComplainDto queryComplainDetail(Long id) {
        Complain complain = complainRepo.findById(id);
        if(complain == null){
            throw new UcBizException(UcReturnCode.UC200045.getMsg(),UcReturnCode.UC200045.getCode());
        }
        List<String> imagePaths = complainRepo.findComplainImages(complain.getComplainId().getId());
        ComplainDto complainDto = ComplainAssermbler.complainDto(complain);
        complainDto.setImagePaths(imagePaths);
        return complainDto;
    }

	 /**
     * 查询列表
     * @param query
     */
    public PageInfo<ComplainDto> queryList(ComplainQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Complain complainQry = new  Complain();
        BeanUtils.copyProperties(query,complainQry);
        List<Complain> complainList = complainRepo.findAll(complainQry);
        List<ComplainDto> complainDtos = new ArrayList<>();
        for (Complain complain : complainList) {
            List<String> imagePaths = complainRepo.findComplainImages(complain.getComplainId().getId());
            ComplainDto complainDto = ComplainAssermbler.complainDto(complain);
            complainDto.setImagePaths(imagePaths);
            complainDtos.add(complainDto);
        }

        PageInfo pageInfo = new PageInfo<>(complainList);
        pageInfo.setList(complainDtos);
        return pageInfo;
    }
}

	
