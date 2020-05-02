package com.jinshuo.mall.service.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/10/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuShopButtonDto {
    private String action;
    private String describe;
    private Boolean defaultCheck;
}
