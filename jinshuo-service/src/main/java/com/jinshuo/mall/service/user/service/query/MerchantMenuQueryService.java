package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.merchant.MerchantMenu;
import com.jinshuo.mall.service.user.application.dto.MenuDto;
import com.jinshuo.mall.service.user.application.dto.MenuShopDto;
import com.jinshuo.mall.service.user.application.dto.MenuTreeDto;
import com.jinshuo.mall.service.user.mybatis.MerchantMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class MerchantMenuQueryService {


    @Autowired
    private MerchantMenuRepo merchantMenuRepo;

    @Autowired
    private MenuQueryService menuQueryService;


    /**
     * 根据店铺id查询店铺的菜单
     *
     * @return Address
     */
    public List<MenuTreeDto> getMyMenu(Long shopId) {
        List<MerchantMenu> merchantMenus = merchantMenuRepo.findAll(shopId);
        List<MenuTreeDto> dtos = menuQueryService.getMenuTree();
        List<MenuTreeDto> result = new ArrayList<>();
        MenuTreeDto menuTreeDto;
        for (MenuTreeDto dto : dtos) {
            for (MerchantMenu merchantMenu : merchantMenus) {
                if (dto.getId().longValue() == merchantMenu.getMenuId().longValue()) {
                    menuTreeDto = MenuTreeDto.builder().id(dto.getId()).name(dto.getName()).type(dto.getType()).code(dto.getCode()).build();
                    if (null != dto.getChilds() && dto.getChilds().size() > 0) {
                        changeChild(menuTreeDto, dto.getChilds(), merchantMenus);
                    }
                    result.add(menuTreeDto);
                }
            }
        }
        return result;
    }

    /**
     * 设置子菜单
     *
     * @param tree
     * @param dtos
     * @param merchantMenus
     * @return
     */
    public MenuTreeDto changeChild(MenuTreeDto tree, List<MenuTreeDto> dtos, List<MerchantMenu> merchantMenus) {
        MenuTreeDto treeDto = new MenuTreeDto();
        List<MenuTreeDto> dtoList = new ArrayList<>();
        for (MenuTreeDto dto : dtos) {
            for (MerchantMenu merchantMenu : merchantMenus) {
                if (dto.getId().longValue() == merchantMenu.getMenuId().longValue()) {
                    treeDto = MenuTreeDto.builder().id(dto.getId()).name(dto.getName()).type(dto.getType()).code(dto.getCode()).build();
                    if (null != dto.getChilds() && dto.getChilds().size() > 0) {
                        changeChild(treeDto, dto.getChilds(), merchantMenus);
                    }
                    dtoList.add(treeDto);
                }
            }
        }
        tree.setChilds(dtoList);
        return tree;
    }

    public List<MenuShopDto> getByShopId(Long shopId) {
        List<MenuShopDto> list = new ArrayList<>();
        List<MerchantMenu> merchantMenus = merchantMenuRepo.findAll(shopId);
        List<MenuDto> dtos = menuQueryService.getAllMenu();
        MenuShopDto menuShopDto;
        for(MenuDto dto:dtos){
            for (MerchantMenu merchantMenu:merchantMenus) {
                if(dto.getId().longValue()==merchantMenu.getMenuId().longValue()){
                    menuShopDto = MenuShopDto.builder().permissionId(dto.getCode()).permissionName(dto.getName()).build();
                    list.add(menuShopDto);
                }
            }
        }
        return list;
    }


    public List<MenuShopDto> getByShopIdAndUserId(Long shopId) {
        Long userId = UserIdUtils.getUserId();

        List<MenuShopDto> list = new ArrayList<>();
        List<MerchantMenu> merchantMenus = merchantMenuRepo.findAll(shopId);
        List<MenuDto> dtos = menuQueryService.getAllMenu();
        MenuShopDto menuShopDto;
        for(MenuDto dto:dtos){
            for (MerchantMenu merchantMenu:merchantMenus) {
                if(dto.getId().longValue()==merchantMenu.getMenuId().longValue()){
                    menuShopDto = MenuShopDto.builder().permissionId(dto.getCode()).permissionName(dto.getName()).build();
                    list.add(menuShopDto);
                }
            }
        }
        return list;
    }


}
