package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

/**
 * Created by 19458 on 2019/11/18.
 */
@Data
public class ParameterValueCmd {
    private Long parameterId;
    private String name; //参数名称
    private String param;//参数值
    private Integer type;// 0-> 可填充 1-> 标题  2-> 空白
    private Integer sort;// 0-> 可填充 1-> 标题  2-> 空白
}
