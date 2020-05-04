package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.marketing.Marketing;
import com.jinshuo.mall.domain.item.marketing.MarketingId;
import com.jinshuo.mall.service.item.application.cmd.MarketingCmd;
import com.jinshuo.mall.service.item.mybatis.MarketingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class MarketingComService {

    @Autowired
    private MarketingRepo marketingRepo;

    @Autowired
    private MarketingProdutComService marketingProdutComService;


    /**
     * 新增活动
     *
     * @param cmd
     */
    @Transactional
    public void create(MarketingCmd cmd) {
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        if (null != cmd.getId()) {
            update(cmd);
        } else {
            Marketing marketing = Marketing.builder()
                    .name(cmd.getName())
                    .marketingType(cmd.getMarketingType())
                    .shopId(cmd.getShopId())
                    .desc(cmd.getDesc())
                    .goodsMode(cmd.getGoodsMode())
                    .startTime(cmd.getStartTime())
                    .endTime(cmd.getEndTime())
                    .quantity(cmd.getQuantity())
                    .sort(cmd.getSort())
                    .marketingStatus(cmd.getMarketingStatus())
                    .build();
            marketing.insert();
            marketingRepo.save(marketing);
            marketingProdutComService.create(cmd.getList(),marketing.getMarketingId().getId());
        }
    }

    /**
     * 修改活动
     *
     * @param cmd
     */
    @Transactional
    public void update(MarketingCmd cmd) {
        Marketing marketing = Marketing.builder()
                .marketingId(new MarketingId(CommonSelfIdGenerator.generateId()))
                .name(cmd.getName())
                .marketingType(cmd.getMarketingType())
                .shopId(cmd.getShopId())
                .desc(cmd.getDesc())
                .goodsMode(cmd.getGoodsMode())
                .startTime(cmd.getStartTime())
                .endTime(cmd.getEndTime())
                .quantity(cmd.getQuantity())
                .sort(cmd.getSort())
                .marketingStatus(cmd.getMarketingStatus())
                .build();
        marketing.update();
        marketingRepo.update(marketing);
        marketingProdutComService.create(cmd.getList(),marketing.getMarketingId().getId());
    }

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return marketingRepo.delete(id);
    }

    /**
     * 修改活动状态
     *
     * @param id
     * @return
     */
    public int updateStatus(Long id, Integer adStatus) {
        return marketingRepo.updateStatus(id, adStatus);
    }
}
