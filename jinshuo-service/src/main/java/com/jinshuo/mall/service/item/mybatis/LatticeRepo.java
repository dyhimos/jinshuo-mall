package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.lattice.Lattice;
import com.jinshuo.mall.service.item.mybatis.mapper.LatticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class LatticeRepo {

    @Autowired(required = false)
    private LatticeMapper latticeMapper;

    public int save(Lattice lattice) {
        return latticeMapper.create(lattice);
    }

    public int update(Lattice lattice) {
        return latticeMapper.update(lattice);
    }

    public List<Lattice> findAll(Long shopId) {
        List<Lattice> list = latticeMapper.findAll(shopId);
        return list;
    }

    public List<Lattice> findShow(Long shopId) {
        List<Lattice> list = latticeMapper.queryShow(shopId);
        return list;
    }

    public int delete(Long id) {
        return latticeMapper.delete(id);
    }

    public Lattice findById(Long id) {
        return latticeMapper.queryById(id);
    }

    public int updateShow(Lattice lattice) {
        return latticeMapper.updateShow(lattice);
    }
}
