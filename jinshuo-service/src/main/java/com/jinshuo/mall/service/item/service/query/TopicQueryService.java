package com.jinshuo.mall.service.item.service.query;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.topic.Topic;
import com.jinshuo.mall.service.item.application.assermbler.TopicAssembler;
import com.jinshuo.mall.service.item.application.dto.TopicDto;
import com.jinshuo.mall.service.item.application.qry.ThemeQry;
import com.jinshuo.mall.service.item.application.qry.TopicQry;
import com.jinshuo.mall.service.item.mybatis.TopicRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class TopicQueryService {

    @Autowired
    private TopicRepo topicRepo;


    /**
     * 获取活动（主题）分页
     *
     * @param qry
     * @return
     */
    public PageInfo getTopicPage(TopicQry qry) {
        Long shopId = 10088l;
        Topic temp = Topic.builder()
                .shopId(shopId)
                .type(qry.getType())
                .build();
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Topic> list = topicRepo.findAll(temp);
        PageInfo pageInfo = new PageInfo(list);
        List<TopicDto> dtos = list.stream()
                .map(topic -> TopicAssembler.assembleDto(topic)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 获取活动（主题）集合
     *
     * @param type
     * @return
     */
    public List<TopicDto> getTopics(Integer type) {
        Long shopId = 10088l;
        Topic temp = Topic.builder()
                .shopId(shopId)
                .type(type)
                .build();
        List<Topic> list = topicRepo.findAll(temp);
        List<TopicDto> dtos = list.stream()
                .map(topic -> TopicAssembler.assembleDto(topic)).collect(Collectors.toList());
        return dtos;
    }

    public TopicDto getTopic(Long id) {
        return TopicAssembler.assembleDto(topicRepo.queryById(id));
    }

    public List<TopicDto> getTheme(Long id) {
        List<Topic> list = topicRepo.findByPid(id);
        List<TopicDto> dtos = list.stream()
                .map(topic -> TopicAssembler.assembleDto(topic)).collect(Collectors.toList());
        return dtos;
    }

    public TopicDto getThemeByCode(ThemeQry qry) {
        log.info(" -- 根据活动code查询主题列表,输入参数：" + JSONObject.toJSONString(qry));
        Topic temp = topicRepo.queryByCode(qry.getCode(), qry.getShopId());
        if (null == temp) {
            return null;
        }
        TopicDto dto = TopicAssembler.assembleDto(temp);
        List<Topic> list = topicRepo.findByPid(temp.getTopicId().getId());
        List<TopicDto> dtos = list.stream()
                .map(topic -> TopicAssembler.assembleDto(topic)).collect(Collectors.toList());
        dto.setList(dtos);
        return dto;
    }
}
