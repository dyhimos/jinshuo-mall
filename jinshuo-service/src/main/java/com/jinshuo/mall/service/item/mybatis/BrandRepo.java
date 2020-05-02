package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.brand.Brand;
import com.jinshuo.mall.service.item.application.qry.BrandQry;
import com.jinshuo.mall.service.item.mybatis.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class BrandRepo {

    @Autowired(required = false)
    private BrandMapper brandMapper;


    public void save(Brand brand) {
        brandMapper.create(brand);
    }

    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    /*public PageInfo<Tag> findAll(SpuQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Spu> list = spuMapper.findAll(qry);
        PageInfo<Spu> pageInfo = new PageInfo(list);
        return pageInfo;
    }*/

    public Brand queryById(Long brandId) {
        return brandMapper.queryById(brandId);
    }


    public List<Brand> findAll() {
        List<Brand> list = brandMapper.findAll();
        return list;
    }

    public int delete(Long id) {
        return brandMapper.delete(id);
    }

    public PageInfo<Brand> getByPage(BrandQry qry) {
        //分页查询
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Brand> list = brandMapper.findAll();
        PageInfo<Brand> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
