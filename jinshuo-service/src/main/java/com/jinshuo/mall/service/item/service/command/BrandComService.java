package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.brand.Brand;
import com.jinshuo.mall.domain.item.brand.BrandId;
import com.jinshuo.mall.service.item.application.cmd.BrandCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.BrandUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.BrandRepo;
import com.jinshuo.mall.service.item.service.query.BrandQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class BrandComService {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private BrandQueryService brandQueryService;


    /**
     * 新增品牌
     *
     * @param cmd
     */
    public void create(BrandCreateCmd cmd) {
        Brand brand = Brand.builder()
                .brandId(new BrandId(CommonSelfIdGenerator.generateId()))
                .name(cmd.getName())
                .pictureUrl(cmd.getPictureUrl())
                //.itemNum(cmd.getItemNum())
                .sort(cmd.getSort()).build();
        brand.preInsert();
        brandRepo.save(brand);
    }

    /**
     * 修改品牌
     *
     * @param cmd
     */
    public void update(BrandUpdateCmd cmd) {
        brandQueryService.getOptionById(cmd.getId())
                .map(tag -> tag.update(cmd.getName(), cmd.getPictureUrl(), cmd.getSort()))
                .ifPresent(tag -> brandRepo.update(tag));
    }

    public int delete(Long id) {
        return brandRepo.delete(id);
    }
}
