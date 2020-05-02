package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.type.Type;
import com.jinshuo.mall.service.item.application.assermbler.TypeAssembler;
import com.jinshuo.mall.service.item.application.dto.TypeDto;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class TypeQueryService {

    @Autowired
    private TypeRepo typeRepo;

    public Type getById(Long id) {
        return typeRepo.queryById(id);
    }

    public TypeDto getDtoById(Long id) {
        return TypeAssembler.assembleTypeDto(typeRepo.queryById(id));
    }

    public Optional<Type> getOptionById(Long id) {
        return Optional.ofNullable(typeRepo.queryById(id));
    }

    public List<TypeDto> getAll() {
        List<Type> list = typeRepo.findAll();
        List<TypeDto> dtos = list.stream()
                .map(type -> TypeAssembler.assembleTypeDto(type)).collect(Collectors.toList());
        return dtos;
    }

    public PageInfo getByPage(TagQry qry) {
        PageInfo pageInfo = typeRepo.getByPage(qry);
        List<Type> types = pageInfo.getList();
        List<TypeDto> dtos = types.stream()
                .map(type -> TypeAssembler.assembleTypeDto(type)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
