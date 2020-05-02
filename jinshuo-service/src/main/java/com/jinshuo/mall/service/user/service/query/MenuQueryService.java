package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.service.user.application.assermbler.MenuAssembler;
import com.jinshuo.mall.service.user.application.dto.MenuDto;
import com.jinshuo.mall.service.user.application.dto.MenuTreeDto;
import com.jinshuo.mall.service.user.application.dto.RoleMenuTreeDto;
import com.jinshuo.mall.service.user.mybatis.MenuRepo;
import com.jinshuo.mall.service.user.service.command.MeunCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class MenuQueryService {

    @Autowired
    private MeunCmdService meunCmdService;


    @Autowired
    private MenuRepo menuRepo;

    /**
     * 查询所有菜单
     *
     * @return Address
     */
    public List<MenuDto> getAllMenu() {
        Menu menu = new Menu();
        return menuRepo.findAll(menu)
                .stream()
                .map(menu1 -> MenuAssembler.assembleDto(menu1)).collect(Collectors.toList());
    }

    /**
     * 查询所有菜单
     *
     * @return Address
     */
    public List<RoleMenuTreeDto> getAllMenuTree() {
        //查询所有菜单
        List<Menu> allMenu = menuRepo.findAll(null);

        List<Long> checkIds = new ArrayList<>();
        //查出一级菜单
        List<RoleMenuTreeDto> menuTreeList = allMenu.stream().filter(menu -> menu.getPid() == 0).map(menu ->
                MenuAssembler.assembleRoleMenuTreeDto(menu)).collect(Collectors.toList());
        meunCmdService.getChildCheck(menuTreeList, allMenu, checkIds);
        return menuTreeList;
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    public List<MenuTreeDto> getMenuTree() {
        List<MenuTreeDto> dtos = menuRepo.findAll(Menu.builder().type(1).build())
                .stream().map(menu -> MenuAssembler.assembleTreeDto(menu))
                .collect(Collectors.toList());
        for (MenuTreeDto dto : dtos) {
            changeChild(dto);
        }
        return dtos;
    }


    /**
     * 查询子菜单
     *
     * @param dto
     * @return
     */
    public MenuTreeDto changeChild(MenuTreeDto dto) {
        List<MenuTreeDto> dtos = menuRepo.findAll(Menu.builder().pid(dto.getId()).build())
                .stream().map(menu -> MenuAssembler.assembleTreeDto(menu))
                .collect(Collectors.toList());
        if (null == dtos) {
            return dto;
        }
        for (MenuTreeDto dto1 : dtos) {
            changeChild(dto1);
        }
        dto.setChilds(dtos);
        return dto;
    }


}
