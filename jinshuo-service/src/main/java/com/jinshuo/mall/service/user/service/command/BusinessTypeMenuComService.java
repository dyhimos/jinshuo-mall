package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.mall.domain.user.model.Menu.BusinessTypeMenu;
import com.jinshuo.mall.service.user.application.cmd.BusinessTypeMenuCmd;
import com.jinshuo.mall.service.user.mybatis.BusinessTypeMenuRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19458 on 2019/10/14.
 */
@Slf4j
@Service
public class BusinessTypeMenuComService {

    @Autowired
    private BusinessTypeMenuRepo businessTypeMenuRepo;


    /**
     * 根据业务类型设置惨淡权限
     *
     * @param cmd
     * @return
     */
    public int save(BusinessTypeMenuCmd cmd) {
        List<BusinessTypeMenu> businessTypeMenus = new ArrayList<>();
        BusinessTypeMenu businessTypeMenu;
        for (Long menuId : cmd.getMenuIds()) {
            businessTypeMenu = new BusinessTypeMenu();
            businessTypeMenu.build(cmd.getBusinessTypeId(), menuId);
            businessTypeMenus.add(businessTypeMenu);
        }
        return businessTypeMenuRepo.save(businessTypeMenus);
    }

    /**
     * 删除菜单权限
     */
    public int delete(Long id) {
        return businessTypeMenuRepo.delete(id);
    }

    /**
     * 根据业务类型查询菜单权限
     *
     * @param businessTypeId
     * @return
     */
    public List<BusinessTypeMenu> getByBusinessTypeId(Integer businessTypeId) {
        return businessTypeMenuRepo.queryByBusinessTypeId(businessTypeId);
    }

}
