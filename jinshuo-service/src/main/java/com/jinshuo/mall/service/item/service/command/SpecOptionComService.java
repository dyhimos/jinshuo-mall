package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.spec.SpecId;
import com.jinshuo.mall.domain.item.spec.SpecOption;
import com.jinshuo.mall.domain.item.spec.SpecOptionId;
import com.jinshuo.mall.service.item.application.cmd.SpecCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpecOptionAloneCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpecOptionAloneUpdateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpecUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SpecOptionRepo;
import com.jinshuo.mall.service.item.service.query.SpecOptionQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/17.
 */
@Service
public class SpecOptionComService {

    @Autowired
    private SpecOptionRepo specOptionRepo;

    @Autowired
    private SpecOptionQueryService specOptionQueryService;

    /**
     * 新增规格参数
     *
     * @param cmd
     */
    @Transactional
    public void create(SpecId specId, SpecCreateCmd cmd) {
        List<SpecOption> specOptions =
                cmd.getSpecOptions().stream()
                        .map(specOptionCreateCmd -> {
                            SpecOption specOption = SpecOption.builder()
                                    .name(specOptionCreateCmd.getName())
                                    .sort(specOptionCreateCmd.getSort())
                                    .specId(specId)
                                    .specOptionId(new SpecOptionId(CommonSelfIdGenerator.generateId())).build();
                            specOption.preInsert();
                            return specOption;
                        }).collect(Collectors.toList());
        specOptions.forEach(specOption -> specOptionRepo.save(specOption));
    }

    /**
     * 修改规格参数（先删除原有规格参数，再新增修改后的规格参数）
     *
     * @param cmd
     */
    @Transactional
    public void update(SpecUpdateCmd cmd) {
        //刪除原有的再新增
        specOptionRepo.deleteBySpecId(cmd.getId());
        List<SpecOption> specOptions =
                cmd.getOptions().stream()
                        .map(specOptionCreateCmd -> {
                            SpecOption specOption = SpecOption.builder()
                                    .name(specOptionCreateCmd.getName())
                                    .sort(specOptionCreateCmd.getSort())
                                    .specId(new SpecId(cmd.getId()))
                                    .specOptionId(new SpecOptionId(CommonSelfIdGenerator.generateId())).build();
                            specOption.preInsert();
                            return specOption;
                        }).collect(Collectors.toList());
        specOptions.forEach(specOption -> specOptionRepo.save(specOption));
    }

    /**
     * 创建规格参数
     *
     * @param cmd
     */
    public void create(SpecOptionAloneCreateCmd cmd) {
        SpecOption specOption = SpecOption.builder()
                .specOptionId(new SpecOptionId(CommonSelfIdGenerator.generateId()))
                .specId(new SpecId(cmd.getSpecId()))
                .name(cmd.getName())
                .sort(cmd.getSort())
                .build();
        specOption.preInsert();
        specOptionRepo.save(specOption);
    }

    /**
     * 修改规格参数
     *
     * @param cmd
     */
    @Transactional
    public void update(SpecOptionAloneUpdateCmd cmd) {
        specOptionQueryService.findOptionalById(cmd.getId())
                .map(specOption -> specOption.update(cmd.getName(), new SpecId(cmd.getSpecId()), cmd.getSort()))
                .ifPresent(specOption -> specOptionRepo.update(specOption));
    }

    /**
     * 删除规格参数
     *
     * @param id
     */
    public void deleteOption(Long id) {
        specOptionQueryService.findOptionalById(id)
                .ifPresent(specOption -> specOptionRepo.deleteById(id));
    }
}
