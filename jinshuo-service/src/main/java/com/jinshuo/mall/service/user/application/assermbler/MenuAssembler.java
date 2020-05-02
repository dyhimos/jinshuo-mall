package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.service.user.application.dto.MenuDto;
import com.jinshuo.mall.service.user.application.dto.MenuTreeDto;
import com.jinshuo.mall.service.user.application.dto.RoleMenuTreeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname MenuAssembler
 * @Date 2019/9/17 18:27
 * @Created by dyh
 */
@Component
public class MenuAssembler {

    /**
     * 菜单dto
     *
     * @param menu
     * @return
     */
    public static MenuDto assembleDto(Menu menu) {
        if (null == menu) {
            return null;
        }
        MenuDto dto = new MenuDto();
        BeanUtils.copyProperties(menu, dto);
        if (null != menu.getMenuId()) {
            dto.setId(menu.getMenuId().getId());
        }
        return dto;
    }


    /**
     * 转化为列表dto
     *
     * @param menu
     * @return
     */
    public static MenuTreeDto assembleTreeDto(Menu menu) {
        if (null == menu) {
            return null;
        }
        MenuTreeDto memberDto = new MenuTreeDto();
        BeanUtils.copyProperties(menu, memberDto);
        memberDto.setId(menu.getMenuId().getId());
        return memberDto;
    }


    /**
     * 转化为列表dto
     *
     * @param menu
     * @return
     */
    public static RoleMenuTreeDto assembleRoleMenuTreeDto(Menu menu) {
        if (null == menu) {
            return null;
        }
        RoleMenuTreeDto roleMenuTreeDto = new RoleMenuTreeDto();
        roleMenuTreeDto.setMenu(menu.getCode());
        roleMenuTreeDto.setName(menu.getName());
        roleMenuTreeDto.setId(menu.getMenuId().getId());
        return roleMenuTreeDto;
    }
}
