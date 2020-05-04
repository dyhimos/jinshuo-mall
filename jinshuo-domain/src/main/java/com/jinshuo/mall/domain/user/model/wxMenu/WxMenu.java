package com.jinshuo.mall.domain.user.model.wxMenu;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Classname WxMenu
 * @Description 微信菜单
 * @Date 2019/6/16 :01
 * @Created by mgh
 * @author mgh
 */
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMenu  extends IdentifiedEntity {

    /**
     * id
     */
    private WxMenuId wxMenuId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 链接
     */
    private String url;

    /**
     * 链接类型
     */
    private String type;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字菜单集合
     */
    public List<WxMenu> childMenuList;

}
