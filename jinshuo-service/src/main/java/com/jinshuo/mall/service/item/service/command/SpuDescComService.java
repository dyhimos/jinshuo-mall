package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.desc.SpuDesc;
import com.jinshuo.mall.domain.item.desc.SpuDescId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.service.item.application.cmd.SpuDescCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuDescUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SpuDescRepo;
import com.jinshuo.mall.service.item.service.query.SpuDescQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/18.
 */
@Service
public class SpuDescComService {

    @Autowired
    private SpuDescRepo spuDescRepo;

    @Autowired
    private SpuDescQueryService spuDescQueryService;


    /**
     * 新增商品详细描述
     *
     * @param cmd
     */
    public void create(SpuDescCreateCmd cmd) {
        SpuDesc spuDesc1 = spuDescQueryService.findSpuDescBySpuId(cmd.getSpuId());
        if (spuDesc1 != null) {
            spuDesc1.update(cmd.getPcDesc(), cmd.getMobileDesc(), cmd.getBookingNotes());
            spuDescRepo.update(spuDesc1);
        } else {
            SpuDesc spuDesc = SpuDesc.builder()
                    .mobileDesc(cmd.getMobileDesc())
                    .pcDesc(cmd.getPcDesc())
                    .spuId(new SpuId(cmd.getSpuId()))
                    .spuDescId(new SpuDescId(CommonSelfIdGenerator.generateId()))
                    .bookingNotes(cmd.getBookingNotes())
                    .build();
            spuDesc.preInsert();
            spuDescRepo.save(spuDesc);
        }
    }

    /**
     * 修改商品详细描述
     *
     * @param cmd
     */
    public void update(SpuDescUpdateCmd cmd) {
        spuDescQueryService.findOptionById(cmd.getId())
                .map(spuDesc1 -> spuDesc1.update(cmd.getPcDesc(), cmd.getMobileDesc(), cmd.getBookingNotes())
                )
                .ifPresent(spuDesc2 -> spuDescRepo.update(spuDesc2)
                );
    }
}
