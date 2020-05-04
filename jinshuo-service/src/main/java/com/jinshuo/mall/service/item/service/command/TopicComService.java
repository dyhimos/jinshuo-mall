package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.mall.domain.item.topic.Topic;
import com.jinshuo.mall.domain.item.topic.TopicId;
import com.jinshuo.mall.service.item.application.cmd.TopicCmd;
import com.jinshuo.mall.service.item.mybatis.TopicRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class TopicComService {

    @Autowired
    private TopicRepo topicRepo;


    /**
     * 保存活动
     *
     * @param cmd
     */
    public void create(TopicCmd cmd) {
        cmd.check();
        if(null==cmd.getShopId()){
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        Topic temp = null;
        if (StringUtils.isNotBlank(cmd.getCode())) {
            temp = topicRepo.queryByCode(cmd.getCode(),cmd.getShopId());
        }
        if (null == cmd.getId() && null != temp) {
            throw new IcBizException(IcReturnCode.IC201017.getCode(), IcReturnCode.IC201017.getMsg());
        }
        if (null != temp && null != cmd.getId() && temp.getTopicId().getId().longValue() != cmd.getId().longValue()) {
            throw new IcBizException(IcReturnCode.IC201017.getCode(), IcReturnCode.IC201017.getMsg());
        }
        if (null != cmd.getId()) {
            update(cmd);
        } else {
            Topic topic = Topic.builder()
                    .name(cmd.getName())
                    .type(cmd.getType())
                    .pid(cmd.getPid())
                    .startTime(cmd.getStartTime())
                    .endTime(cmd.getEndTime())
                    .topicStatus(cmd.getTopicStatus())
                    .topicDesc(cmd.getTopicDesc())
                    .mainPicture(cmd.getMainPicture())
                    .signPicture(cmd.getSignPicture())
                    .posterPicture(cmd.getPosterPicture())
                    .color(cmd.getColor())
                    .shopId(cmd.getShopId())
                    .code(cmd.getCode())
                    .sort(cmd.getSort())
                    .showType(cmd.getShowType())
                    .headingShowFlag(cmd.getHeadingShowFlag())
                    .headingColor(cmd.getHeadingColor())
                    .build();
            topic.insert();
            topicRepo.save(topic);
        }
    }

    /**
     * 修改活动
     *
     * @param cmd
     */
    public void update(TopicCmd cmd) {
        Topic topic = Topic.builder()
                .topicId(new TopicId(cmd.getId()))
                .name(cmd.getName())
                .type(cmd.getType())
                .pid(cmd.getPid())
                .startTime(cmd.getStartTime())
                .endTime(cmd.getEndTime())
                .topicStatus(cmd.getTopicStatus())
                .topicDesc(cmd.getTopicDesc())
                .mainPicture(cmd.getMainPicture())
                .signPicture(cmd.getSignPicture())
                .posterPicture(cmd.getPosterPicture())
                .color(cmd.getColor())
                .shopId(cmd.getShopId())
                .code(cmd.getCode())
                .sort(cmd.getSort())
                .showType(cmd.getShowType())
                .headingShowFlag(cmd.getHeadingShowFlag())
                .headingColor(cmd.getHeadingColor())
                .build();
        topicRepo.update(topic);
    }

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return topicRepo.delete(id);
    }

    /**
     * 修改活动状态
     *
     * @param id
     * @return
     */
    public int updateStatus(Long id, Integer adStatus) {
        return topicRepo.updateStatus(id, adStatus);
    }
}
