package com.jinshuo.mall.domain.user.model.problem;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/4/12.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem extends IdentifiedEntity {
    private ProblemId problemId;
    private String title;
    private String content;
    private Integer sort;

    public void init() {
        this.problemId = new ProblemId(CommonSelfIdGenerator.generateId());
        super.preInsert();
    }

    public void update() {

    }
}
