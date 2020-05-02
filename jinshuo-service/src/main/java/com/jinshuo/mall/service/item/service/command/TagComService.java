package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.tag.Tag;
import com.jinshuo.mall.domain.item.tag.TagId;
import com.jinshuo.mall.service.item.application.cmd.TagCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.TagUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.TagRepo;
import com.jinshuo.mall.service.item.service.query.TagQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class TagComService {

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private TagQueryService tagQueryService;

    /**
     * 新增标签
     *
     * @param cmd
     */
    public void create(TagCreateCmd cmd) {
        Tag tag = Tag.builder()
                .tagId(new TagId(CommonSelfIdGenerator.generateId()))
                .name(cmd.getName())
                .sort(cmd.getSort())
                .shopId(cmd.getShopId())
                .build();
        tag.preInsert();
        tagRepo.save(tag);
    }

    /**
     * 修改标签
     *
     * @param cmd
     */
    public void update(TagUpdateCmd cmd) {
        tagQueryService.getOptionById(cmd.getId())
                .map(tag -> tag.update(cmd.getName(), cmd.getSort(),cmd.getShopId()))
                .ifPresent(tag -> tagRepo.update(tag));
    }

    public int delete(Long id) {
        return tagRepo.delete(id);
    }
}
