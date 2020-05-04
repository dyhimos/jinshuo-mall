package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.mall.domain.item.service.ServiceSupport;
import com.jinshuo.mall.service.item.application.assermbler.ServiceAssembler;
import com.jinshuo.mall.service.item.application.dto.ServiceSupportDto;
import com.jinshuo.mall.service.item.application.qry.TopicQry;
import com.jinshuo.mall.service.item.mybatis.ServiceSupportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class ServiceSupportQueryService {

    @Autowired
    private ServiceSupportRepo serviceSupportRepo;


    /**
     * 分页查询服务
     *
     * @param qry
     * @return
     */
    public PageInfo getSercivePage(TopicQry qry) {
        ServiceSupport serviceSupport = new ServiceSupport();
        serviceSupport.setShopId(DefaultShopId.SHOPID);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<ServiceSupport> list = serviceSupportRepo.findAll(serviceSupport);
        PageInfo pageInfo = new PageInfo(list);
        List<ServiceSupportDto> dtos = list.stream()
                .map(serviceSupport1 -> ServiceAssembler.assembleDto(serviceSupport1)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 获取服务集合
     *
     * @return
     */
    public List<ServiceSupportDto> getServices() {
        ServiceSupport serviceSupport = new ServiceSupport();
        serviceSupport.setShopId(DefaultShopId.SHOPID);
        List<ServiceSupport> list = serviceSupportRepo.findAll(serviceSupport);
        List<ServiceSupportDto> dtos = list.stream()
                .map(serviceSupport1 -> ServiceAssembler.assembleDto(serviceSupport1)).collect(Collectors.toList());
        return dtos;
    }

    //获取服务
    public String changeServiceName(String services) {
        if (StringUtils.isEmpty(services)) {
            return "";
        }
        List<ServiceSupportDto> list = getServices();
        StringBuffer stringBuffer = new StringBuffer("");
        for (ServiceSupportDto dto : list) {
            if (services.indexOf(dto.getId()) > -1) {
                stringBuffer.append(dto.getName() + ",");
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

}
