package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.collect.Collect;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.user.application.assermbler.CollectAssembler;
import com.jinshuo.mall.service.user.application.dto.UserCollectSpuDto;
import com.jinshuo.mall.service.user.application.qry.CollectQry;
import com.jinshuo.mall.service.user.mybatis.CollectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class CollectQueryService {

    @Autowired
    private CollectRepo collectRepo;

    @Autowired
    private SpuQueryService spuQueryService;

   /* @Autowired
    private ItemServiceResponse itemServiceResponse;*/

    public Collect getById(Long id) {
        return collectRepo.queryById(id);
    }

    public Optional<Collect> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    public PageInfo getByPage(CollectQry qry) {
        qry.setMemId(UserIdUtils.getUserId());
        PageInfo pageInfo = collectRepo.getByPage(qry);
        List<Collect> collects = pageInfo.getList();
        List<UserCollectSpuDto> dtos = collects.stream()
                .map(collect -> {
                    SpuQry qry1 = new SpuQry();
                    qry1.setId(collect.getType(), collect.getTargetId());
                    //UserCollectSpuDto dto = CollectAssembler.assembleUserCollectSpuDto(itemServiceResponse.getByExemple(qry1), collect.getCollectId().getId());
                    UserCollectSpuDto dto = CollectAssembler.assembleUserCollectSpuDto(spuQueryService.findByExemple(qry1), collect.getCollectId().getId());
                    return dto;
                }).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }


}
