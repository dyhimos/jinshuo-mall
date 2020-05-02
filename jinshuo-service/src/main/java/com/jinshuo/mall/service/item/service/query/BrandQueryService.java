package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.brand.Brand;
import com.jinshuo.mall.service.item.application.assermbler.BrandAssembler;
import com.jinshuo.mall.service.item.application.dto.BrandDto;
import com.jinshuo.mall.service.item.application.qry.BrandQry;
import com.jinshuo.mall.service.item.mybatis.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class BrandQueryService {

    @Autowired
    private BrandRepo brandRepo;

    public Brand getById(Long id) {
        return brandRepo.queryById(id);
    }

    public Optional<Brand> getOptionById(Long id) {
        return Optional.ofNullable(brandRepo.queryById(id));
    }

    public List<BrandDto> getAll() {
        List<Brand> list = brandRepo.findAll();
        List<BrandDto> dtos = list.stream()
                .map(brand -> BrandAssembler.assembleBrandDto(brand)).collect(Collectors.toList());
        return dtos;
    }

    public PageInfo getCategorysByPage(BrandQry qry) {
        PageInfo pageInfo = brandRepo.getByPage(qry);
        List<Brand> categories = pageInfo.getList();
        List<BrandDto> dtos = categories.stream().map(category -> BrandAssembler.assembleBrandDto(category)).collect(Collectors.toList());
        //List<CategoryDto> dtos1 = dtos.stream().map(categoryDto -> categoryDto.setPname(""));
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
