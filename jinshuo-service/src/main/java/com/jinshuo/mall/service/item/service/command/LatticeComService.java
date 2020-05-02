package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.mall.domain.item.lattice.Lattice;
import com.jinshuo.mall.domain.item.lattice.LatticeId;
import com.jinshuo.mall.service.item.application.cmd.LatticeCmd;
import com.jinshuo.mall.service.item.mybatis.LatticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class LatticeComService {

    @Autowired
    private LatticeRepo latticeRepo;


    /**
     * 新增菜单格子
     *
     * @param cmd
     */
    public int create(LatticeCmd cmd) {
        Lattice lattice = Lattice.builder()
                .name(cmd.getName())
                .shopId(cmd.getShopId())
                .orderSeq(cmd.getOrderSeq())
                .pictureUrl(cmd.getPictureUrl())
                .isShow(cmd.getIsShow())
                .build();
        if (null == cmd.getId()) {
            lattice.insert();
            return latticeRepo.save(lattice);
        } else {
            lattice.update(cmd.getId());
            return latticeRepo.update(lattice);
        }
    }


    /**
     * 修改展示状态
     *
     * @return
     */
    public int updateShow(LatticeCmd cmd) {
        Lattice lattice = Lattice.builder()
                .isShow(cmd.getIsShow())
                .latticeId(new LatticeId(cmd.getId()))
                .build();
        return latticeRepo.updateShow(lattice);
    }

    /**
     * 删除菜单格子
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return latticeRepo.delete(id);
    }
}
