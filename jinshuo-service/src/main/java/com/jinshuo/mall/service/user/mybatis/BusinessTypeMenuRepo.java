package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.Menu.BusinessTypeMenu;
import com.jinshuo.mall.service.user.mybatis.mapper.BusinessTypeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class BusinessTypeMenuRepo {

    @Autowired(required = false)
    private BusinessTypeMenuMapper businessTypeMenuMapper;

    public int save(List<BusinessTypeMenu> businessTypeMenus) {
        return businessTypeMenuMapper.create(businessTypeMenus);
    }

    public List<BusinessTypeMenu> queryByBusinessTypeId(Integer businessTypeId) {
        return businessTypeMenuMapper.queryMyMenu(businessTypeId);
    }

    public int delete(Long businessTypeMenuId) {
        return businessTypeMenuMapper.delete(businessTypeMenuId);
    }

}
