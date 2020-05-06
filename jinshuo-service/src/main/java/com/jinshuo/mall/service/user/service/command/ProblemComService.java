package com.jinshuo.mall.service.user.service.command;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.user.model.problem.Problem;
import com.jinshuo.mall.domain.user.model.problem.ProblemId;
import com.jinshuo.mall.service.user.application.assermbler.ProblemAssermbler;
import com.jinshuo.mall.service.user.application.cmd.ProblemCmd;
import com.jinshuo.mall.service.user.application.dto.ProblemDto;
import com.jinshuo.mall.service.user.application.qry.ProblemQry;
import com.jinshuo.mall.service.user.mybatis.ProblemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/4/12.
 */
@Slf4j
@Service
public class ProblemComService {

    @Autowired
    private ProblemRepo problemRepo;


    /**
     * 保存常见问题
     *
     * @param cmd
     * @return
     */
    public Problem save(ProblemCmd cmd) {
        Problem problem = Problem.builder()
                .title(cmd.getTitle())
                .sort(cmd.getSort())
                .content(cmd.getContent())
                .build();
        if (null == cmd.getId()) {
            problem.init();
            problemRepo.save(problem);
            return problem;
        }
        problem.setProblemId(new ProblemId(cmd.getId()));
        problem.update();
        problemRepo.update(problem);
        return problem;
    }


    /**
     * 保存常见问题
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return problemRepo.delete(id);
    }


    /**
     * 查询常见问题
     *
     * @param qry
     * @return
     */
    public PageInfo queryProblems(ProblemQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Problem problem = Problem.builder().title(qry.getTitle()).build();
        List<Problem> list = problemRepo.queryNotice(problem);
        List<ProblemDto> dtos = list.stream().map(problem1 -> ProblemAssermbler.assembleDto(problem1)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }


    public ProblemDto queryDetail(Long id){
        Problem problem = problemRepo.queryById(id);
        return ProblemAssermbler.assembleDto(problem);
    }
}
