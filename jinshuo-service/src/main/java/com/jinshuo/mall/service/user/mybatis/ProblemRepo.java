package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.problem.Problem;
import com.jinshuo.mall.service.user.mybatis.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Repository
public class ProblemRepo {

    @Autowired
    private ProblemMapper problemMapper;


    public int save(Problem problem) {
        return problemMapper.create(problem);
    }

    public int update(Problem problem) {
        return problemMapper.update(problem);
    }

    public Problem queryById(Long id) {
        return problemMapper.queryById(id);
    }

    public int delete(Long id) {
        return problemMapper.delete(id);
    }

    public List<Problem> queryNotice(Problem qry) {
        return problemMapper.queryProblem(qry);
    }

}
