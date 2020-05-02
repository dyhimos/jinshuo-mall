package com.jinshuo.mall.service.order.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * @author dongyh
 * @Title: GoodsFinshedCmd
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/10/22 11:26
 */
@Data
public class GoodsFinshedCmd {
    /**
     * id列表
     */
    private List<Long> ids;

    private Long shopId;
}
