package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.expressCode.ExpressCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 快递mapper
 *
 * @Classname GoodsExpressMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface ExpressCodeMapper {

    @Results(
            id = "expressCodeResult",
            value = {
                    @Result(property = "expressCodeId.id", column = "id"),
                    @Result(property = "expressCompanyName", column = "express_company_name"),
                    @Result(property = "expressCode", column = "express_code")
            }
    )
    @Select("SELECT * FROM express_code WHERE express_company_name=#{expressCompanyName}")
    ExpressCode findExpressByName(String expressCompanyName);

}
