package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.area.City;
import com.jinshuo.mall.domain.item.area.CityId;
import com.jinshuo.mall.service.item.application.assermbler.CityAssembler;
import com.jinshuo.mall.service.item.application.cmd.CityCmd;
import com.jinshuo.mall.service.item.application.cmd.CitysCmd;
import com.jinshuo.mall.service.item.application.dto.CityDto;
import com.jinshuo.mall.service.item.application.dto.CityMainDto;
import com.jinshuo.mall.service.item.mybatis.CityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CityComService {

    @Autowired
    private CityRepo cityRepo;


    /**
     * 开通城市
     *
     * @param cityCmd
     */
    public int create(CityCmd cityCmd) {
        log.info(" -- 开通城市,输入参数：" + JSONObject.toJSONString(cityCmd));
        if (null != cityCmd.getShopId()) {
            cityRepo.deleteByShopId(cityCmd.getShopId());
        }
        for (CitysCmd cmd : cityCmd.getCitysCmdList()) {
            List<City> cities = cityRepo.queryByAreaName(cityCmd.getShopId(), cmd.getAreaName());
            if (cities.size() > 0) {
                continue;
            }
            City city = City.builder()
                    .cityId(new CityId(CommonSelfIdGenerator.generateId()))
                    .areaProName(cmd.getAreaProName())
                    .areaName(cmd.getAreaName())
                    .shopId(cityCmd.getShopId())
                    .build();
            city.insert();
            cityRepo.save(city);
        }
        return cityCmd.getCitysCmdList().size();
    }

    public int delete(Long id) {
        return cityRepo.delete(id);
    }

    public List<String> getAll(Long shopId) {
        List<City> citys = cityRepo.findAllByShopId(shopId);
        List<String> strings = citys.stream().map(city -> city.getAreaName()).collect(Collectors.toList());
        return strings;
    }

    public List<CityMainDto> getCityTree(Long shopId) {
        List<City> citys = cityRepo.findAllByShopId(shopId);
        List<CityDto> dtos = citys.stream().map(city -> CityAssembler.assembleDto(city)).collect(Collectors.toList());
        Map<String, List<CityDto>> cityTree = dtos.stream().collect(Collectors.groupingBy(CityDto::getAreaProName));
        List<CityMainDto> cityMainDtos = new ArrayList<>();
        CityMainDto cityMainDto;
        for (String key : cityTree.keySet()) {
            cityMainDto = new CityMainDto();
            cityMainDto.setAreaProName(key);
            cityMainDto.setCityDtos(cityTree.get(key));
            cityMainDtos.add(cityMainDto);
        }
        return cityMainDtos;
    }
}
