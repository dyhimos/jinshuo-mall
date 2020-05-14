package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.mall.domain.order.product.expressCode.ExpressCode;
import com.jinshuo.mall.service.order.mybatis.mapper.ExpressCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 快递信息
 * @Classname GoodsExpresssRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class ExpresssCodeRepo {

    @Autowired
    private ExpressCodeMapper mapper;


    /**
     * 根据名称查询快递编码
     * @param expressCompanyName 快递公司名称
     * @return
     */
    public ExpressCode findExpressByName(String expressCompanyName){
        ExpressCode expressCode = mapper.findExpressByName(expressCompanyName);
        return expressCode;
    }
}
