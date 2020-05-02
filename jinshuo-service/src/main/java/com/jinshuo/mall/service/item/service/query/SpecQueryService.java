package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.spec.Spec;
import com.jinshuo.mall.service.item.application.assermbler.SpecAssembler;
import com.jinshuo.mall.service.item.application.dto.SpecDto;
import com.jinshuo.mall.service.item.application.dto.UserSpecDto;
import com.jinshuo.mall.service.item.application.qry.SpecQry;
import com.jinshuo.mall.service.item.mybatis.SpecOptionRepo;
import com.jinshuo.mall.service.item.mybatis.SpecRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/17.
 */
@Service
public class SpecQueryService {


    @Autowired
    private SpecRepo specRepo;

    @Autowired
    private SpecOptionRepo specOptionRepo;


    @Autowired
    private SpecOptionQueryService specOptionQueryService;

    public Spec findById(Long id) {
        return specRepo.findById(id);
    }

    public List<SpecDto> getSpecList(SpecQry qry) {
        if (null == qry.getShopId()) {
            qry.setShopId(10088l);
        }
        List<SpecDto> dtos = specRepo.findAll(qry.getShopId()).stream().map(spec -> SpecAssembler.assembleSpecDto(spec)).collect(Collectors.toList());
        dtos.forEach(specDto -> {
            specDto.setOptions(specOptionQueryService.getDtoBySpecId(Long.parseLong(specDto.getSpecId())));
        });
        return dtos;
    }

    public Optional<Spec> findOptionalById(Long id) {
        return Optional.ofNullable(specRepo.findById(id));
    }

    public UserSpecDto getUserDtoById(Long id) {
        return SpecAssembler.assembleUserDto(findById(id));
    }

}
