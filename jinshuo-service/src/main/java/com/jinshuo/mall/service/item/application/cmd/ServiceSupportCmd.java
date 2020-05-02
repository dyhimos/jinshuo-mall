package com.jinshuo.mall.service.item.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/30.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSupportCmd {
    private Long id;
    private Long shopId;
    private String name;
    private String desc;
    private Integer sort;
    private Integer ssStatus;
}
