package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.tag.Tag;
import com.jinshuo.mall.service.item.application.assermbler.TagAssembler;
import com.jinshuo.mall.service.item.application.dto.TagDto;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class TagQueryService {

    @Autowired
    private TagRepo tagRepo;

    public Tag getById(Long id) {
        return tagRepo.queryById(id);
    }

    public TagDto getDtoById(Long id) {
        return TagAssembler.assembleTagDto(tagRepo.queryById(id));
    }

    public Optional<Tag> getOptionById(Long id) {
        return Optional.ofNullable(tagRepo.queryById(id));
    }

    public List<TagDto> getAll() {
        List<Tag> list = tagRepo.findAll();
        List<TagDto> dtos = list.stream()
                .map(tag -> TagAssembler.assembleTagDto(tag)).collect(Collectors.toList());
        return dtos;
    }

    public PageInfo getByPage(TagQry qry) {
        PageInfo pageInfo = tagRepo.getByPage(qry);
        List<Tag> tags = pageInfo.getList();
        List<TagDto> dtos = tags.stream()
                .map(tag -> TagAssembler.assembleTagDto(tag)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
