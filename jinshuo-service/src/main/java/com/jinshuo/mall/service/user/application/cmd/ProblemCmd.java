package com.jinshuo.mall.service.user.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2020/4/12.
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProblemCmd {
    private Long id;
    private String title;
    private String content;
    private Integer sort;
}
