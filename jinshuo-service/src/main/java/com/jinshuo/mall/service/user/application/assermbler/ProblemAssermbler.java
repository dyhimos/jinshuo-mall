package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.problem.Problem;
import com.jinshuo.mall.service.user.application.dto.ProblemDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2020/1/3.
 */
public class ProblemAssermbler {

    /**
     * @param problem
     * @return
     */
    public static ProblemDto assembleDto(Problem problem) {
        if (null == problem) {
            return null;
        }
        ProblemDto dto = new ProblemDto();
        BeanUtils.copyProperties(problem, dto);
        dto.setId(problem.getProblemId().getId());
        return dto;
    }
}
