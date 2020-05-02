package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.parameter.CategoryParameter;
import com.jinshuo.mall.domain.item.parameter.CategoryParameterId;
import com.jinshuo.mall.service.item.application.cmd.CategoryParameterCmd;
import com.jinshuo.mall.service.item.mybatis.CategoryParameterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/3.
 */
@Service
public class CategoryParameterComService {

    @Autowired
    private CategoryParameterRepo categoryParameterRepo;

    public void save(CategoryParameterCmd cmd) {
        for (Long id : cmd.getParameterIds()) {
            CategoryParameter categoryParameter = CategoryParameter.builder()
                    .categoryParameterId(new CategoryParameterId(CommonSelfIdGenerator.generateId()))
                    .parameterId(id)
                    .categoryId(cmd.getCategoryId())
                    .build();
            categoryParameterRepo.save(categoryParameter);
        }
    }

}
