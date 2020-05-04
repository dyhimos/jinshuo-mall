package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.wxMenu.WxMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 微信菜单
 * @Classname WxMenuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by mgh
 */
@Mapper
public interface WxMenuMapper {

    @InsertProvider(type = UserDynamicSql.class, method = "createWxMenuSql")
    void save(WxMenu wxMenu);


    @ResultMap(value="WxMenuResult")
    @Select("SELECT * FROM wx_menu WHERE parent_id=#{id} and status=1 order by sort desc")
    List<WxMenu> queryChildList(Long id);


    @Results(
            id = "WxMenuResult",
            value = {
                    @Result(property = "wxMenuId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "parentId", column = "parent_id"),
                    @Result(column="id", property="childMenuList", javaType=List.class,
                            many=@Many(select="com.ym.uc.infra.mybatis.mapper.WxMenuMapper.queryChildList")),
                    @Result(property = "name", column = "name"),
                    @Result(property = "url", column = "url"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "level", column = "level"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "status", column = "status",javaType = Status.class,typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM wx_menu WHERE shop_id=#{shopId} and level=1 and status=1 order by sort desc")
    public List<WxMenu> findByShopId(@Param("shopId") Long shopId);
}
