package com.jinshuo.mall.service.order.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeLog;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.order.application.assermbler.OrderVerificationCodeLogAssembler;
import com.jinshuo.mall.service.order.application.dto.OrderVerificationCodeLogDto;
import com.jinshuo.mall.service.order.application.qry.LogQry;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderDetailRepo;
import com.jinshuo.mall.service.order.mybatis.GoodsVerificationCodeLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/11/22.
 */
@Service
public class OrderVerificationCodeLogQueryService {

    @Autowired
    private GoodsVerificationCodeLogRepo repo;

    @Autowired
    private GoodsOrderDetailRepo goodsOrderDetailRepo;

    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private GoodsOrderQueryService goodsOrderQueryService;


    public PageInfo getByPage(LogQry qry) throws OcBizException {
        Long supplierId = UserIdUtils.getSupplierByUserId().getSupplierId();
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<OrderVerificationCodeLog> logs = repo.findBySupplierId(supplierId);
        PageInfo pageInfo = new PageInfo(logs);
        List<OrderVerificationCodeLogDto> dtos = logs.stream().map(log ->
                OrderVerificationCodeLogAssembler.assembleCartDto(log))
                .map(orderVerificationCodeLogDto -> setSpuInfo(orderVerificationCodeLogDto))
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    public PageInfo getLogBySpuId(LogQry qry) throws OcBizException {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<OrderVerificationCodeLog> logs = repo.findBySpuId(qry);
        PageInfo pageInfo = new PageInfo(logs);
        List<OrderVerificationCodeLogDto> dtos = logs.stream().map(log ->
                OrderVerificationCodeLogAssembler.assembleCartDto(log))
                .map(orderVerificationCodeLogDto -> setSpuInfo(orderVerificationCodeLogDto))
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }


    public OrderVerificationCodeLogDto setSpuInfo(OrderVerificationCodeLogDto dto) {
        GoodsOrderDetail detail = goodsOrderDetailRepo.findById(dto.getGoodsOrderDetailId());
        dto.setSpuId(detail.getGoodsSpuId());
        dto.setSpuName(detail.getGoodsName());
        dto.setCostPrice(detail.getCostPrice());
        SpuQry spuQry = SpuQry.builder().spuId(dto.getSpuId()).build();
        SpuDto spuDto = spuQueryService.findByExemple(spuQry);
        if (null != spuDto.getPictureUrl()) {
            dto.setUrl(spuDto.getPictureUrl());
        }
        dto.setMemberName(goodsOrderQueryService.findGoodsOrderById(new GoodsOrderId(dto.getGoodsOrderId())).getMemberName());
        return dto;
    }
}
