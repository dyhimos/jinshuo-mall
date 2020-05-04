package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.category.model.CategoryId;
import com.jinshuo.mall.domain.item.spec.Spec;
import com.jinshuo.mall.domain.item.spec.SpecId;
import com.jinshuo.mall.service.item.application.cmd.SpecCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpecUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SpecRepo;
import com.jinshuo.mall.service.item.service.query.SpecQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 19458 on 2019/7/17.
 */
@Service
public class SpecComService {

    @Autowired
    private SpecRepo specRepo;

    @Autowired
    private SpecOptionComService specOptionComService;

    @Autowired
    private SpecQueryService specQueryService;


    /**
     * 新增规格并新增规格参数
     *
     * @param cmd
     */
    @Transactional
    public void create(SpecCreateCmd cmd) {
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        SpecId specId = new SpecId(CommonSelfIdGenerator.generateId());
        Spec spec = Spec.builder()
                .shopId(cmd.getShopId())
                .name(cmd.getName())
                .sort(cmd.getSort())
                .categoryId(new CategoryId(cmd.getCategoryId()))
                .specId(specId)
                .build();
        spec.preInsert();
        specRepo.save(spec);
        specOptionComService.create(specId, cmd);
    }

    /**
     * 修改规格
     *
     * @param cmd
     */
    @Transactional
    public void update(SpecUpdateCmd cmd) {
        specRepo.findOptionalById(cmd.getSpecId())
                .map(spec -> spec.changeName(cmd.getName()))
                .map(spec -> spec.changeName(cmd.getSort()))
                .map(spec -> spec.changeCategory(new CategoryId(cmd.getCategoryId())))
                .map(spec -> {
                    spec.preUpdate();
                    return spec;
                })
                .ifPresent(spec -> specRepo.update(spec));
        //specOptionComService.update(cmd);
    }

    /**
     * 单独新增规格
     *
     * @param cmd
     */
    @Transactional
    public Spec createSpec(SpecCreateCmd cmd) {
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        Spec spec = Spec.builder()
                .shopId(cmd.getShopId())
                .name(cmd.getName())
                .sort(cmd.getSort())
                .categoryId(new CategoryId(cmd.getCategoryId()))
                .specId(new SpecId(CommonSelfIdGenerator.generateId()))
                .build();
        spec.preInsert();
        specRepo.save(spec);
        return spec;
    }

    /**
     * 删除规格
     *
     * @param id
     */
    public void delete(Long id) {
        specQueryService.findOptionalById(id).ifPresent(
                spec -> specRepo.delete(id)
        );
    }


}
