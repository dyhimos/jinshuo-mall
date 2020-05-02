package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.type.Type;
import com.jinshuo.mall.domain.item.type.TypeId;
import com.jinshuo.mall.service.item.application.cmd.TypeCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.TypeUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.TypeRepo;
import com.jinshuo.mall.service.item.service.query.TypeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class TypeComService {

    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private TypeQueryService typeQueryService;

    /**
     * 新增标签
     *
     * @param cmd
     */
    public void create(TypeCreateCmd cmd) {
        Type type = Type.builder()
                .typeId(new TypeId(CommonSelfIdGenerator.generateId()))
                .name(cmd.getName())
                .sort(cmd.getSort())
                .build();
        type.preInsert();
        typeRepo.save(type);
    }

    /**
     * 修改标签
     *
     * @param
     */
    public void update(TypeUpdateCmd cmd) {
        typeQueryService.getOptionById(cmd.getId())
                .map(type -> type.update(cmd.getName(), cmd.getSort()))
                .ifPresent(type -> typeRepo.update(type));
    }

    public int delete(Long id) {
        return typeRepo.delete(id);
    }
}
