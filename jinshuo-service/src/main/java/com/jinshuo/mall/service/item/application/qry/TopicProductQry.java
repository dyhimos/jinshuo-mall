package com.jinshuo.mall.service.item.application.qry;

import lombok.Data;

/**
 * Created by 19458 on 2019/9/30.
 */
@Data
public class TopicProductQry {

    private Integer type; //查询类型 0-》查询尚未选中的产品   1-》查询选中的产品（后台）  3-》查询选中的产品（前台）

    private Long topicId;

    private Integer categoryId;

    private String name;
}
