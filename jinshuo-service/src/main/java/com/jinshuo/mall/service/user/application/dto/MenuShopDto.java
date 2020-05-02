package com.jinshuo.mall.service.user.application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 19458 on 2019/10/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuShopDto {
    private String roleId = "admin";
    private String permissionId;
    private String permissionName;
    private String actions;
    private List<MenuShopButtonDto> actionEntitySet;
}
