package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.mall.domain.item.unit.model.GoodsUnit;
import com.jinshuo.mall.domain.item.unit.model.GoodsUnitId;
import com.jinshuo.mall.service.item.application.cmd.GoodUnitCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.GoodUnitUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.GoodUnitRepo;
import com.jinshuo.mall.service.item.service.query.GoodUnitQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/9.
 */
@Service
public class GoodUnitComService {


    @Autowired
    private GoodUnitRepo repo;

    @Autowired
    private GoodUnitQueryService queryService;

    /**
     * 新增商品单位
     *
     * @param dto
     */
    public void createUnit(GoodUnitCreateCmd dto) {
        GoodsUnit unit = GoodsUnit.builder()
                .unitId(repo.nextId())
                .name(dto.getName())
                .build();
        repo.save(unit);
    }

    /**
     * 修改商品单位
     *
     * @param dto
     */
    public void updateUnit(GoodUnitUpdateCmd dto) {
        queryService.findUnitBaseById(new GoodsUnitId(dto.getId()))
                .map(unit -> unit.changName(dto.getName()))
                //.map(unit -> unit.changStatus(dto.getUnitStatus()))
                .ifPresent(unit -> repo.update(unit));
    }

    public int deleteUnit(Long id) {
        return repo.delete(id);
    }

}
