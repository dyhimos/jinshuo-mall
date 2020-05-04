package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.wx.WxConfig;
import org.apache.ibatis.annotations.*;

/**
 * @Classname UserAccountMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by mgh
 */
@Mapper
public interface WxConfigMapper {

    @InsertProvider(type = UserDynamicSql.class, method = "createWxConfigSql")
    void save(WxConfig wxConfig);

    @Results(
            id = "WxConfigResult",
            value = {
                    @Result(property = "wxConfigId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "appId", column = "app_id"),
                    @Result(property = "appSecret", column = "app_secret"),
                    @Result(property = "appName", column = "app_name"),
                    @Result(property = "payModel", column = "pay_model"),
                    @Result(property = "mchId", column = "mch_id"),
                    @Result(property = "subMchId", column = "sub_mch_id"),
                    @Result(property = "apiKey", column = "api_key"),
                    @Result(property = "certPath", column = "cert_path"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM wx_config WHERE shop_id=#{shopId} and type=#{type}")
    public WxConfig findByShopIdAndType(@Param("shopId") Long shopId, @Param("type") Integer type);
}
