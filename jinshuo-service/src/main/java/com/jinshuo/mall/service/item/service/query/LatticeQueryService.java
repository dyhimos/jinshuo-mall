package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.lattice.Lattice;
import com.jinshuo.mall.service.item.application.assermbler.LatticeAssembler;
import com.jinshuo.mall.service.item.application.dto.LatticeDto;
import com.jinshuo.mall.service.item.application.qry.LatticeQry;
import com.jinshuo.mall.service.item.mybatis.LatticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class LatticeQueryService {

    @Autowired
    private LatticeRepo latticeRepo;


    public List<Lattice> getShowList() {
        Long shopId = 10086l;
        return latticeRepo.findShow(shopId);
    }

    public PageInfo getPageInfo(LatticeQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Long shopId = 10086l;
        List<Lattice> list = latticeRepo.findAll(shopId);
        PageInfo pageInfo = new PageInfo(list);
        List<LatticeDto> dtos = list.stream().map(lattice -> LatticeAssembler.assembleDto(lattice)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 查询首页展示菜单格子
     *
     * @return
     */
    public List<LatticeDto> getFirstPageLattice() {
        List<Lattice> lattices = latticeRepo.findShow(10086l);
        List<LatticeDto> dtos = lattices.stream().map(lattice -> LatticeAssembler.assembleDto(lattice)).collect(Collectors.toList());
        return dtos;
    }

}
