package com.jinshuo.mall.domain.user.model.Menu;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends IdentifiedEntity {
    private MenuId menuId;
    /**
     * 菜单类型 1:一级菜单 2:二级菜单 3:三级菜单
     */
    private Integer type;

    /**
     * 菜单类型 1:系统 2:菜单 3:按钮
     */
    private Integer menuType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 名称
     */
    private String name;

    /**
     * 父节点id
     */
    private Long pid;

    /**
     * 节点编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;
}
