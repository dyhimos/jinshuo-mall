package com.jinshuo.mall.service.order.service.query;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.utils.DateUtil;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.order.OrderStatusEnums;
import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimple;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.order.application.assermbler.GoodsOrderAssembler;
import com.jinshuo.mall.service.order.application.assermbler.GoodsSimpleOrderAssembler;
import com.jinshuo.mall.service.order.application.dto.*;
import com.jinshuo.mall.service.order.application.qry.*;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderDetailRepo;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderRepo;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderSimpleRepo;
import com.jinshuo.mall.service.user.application.dto.*;
import com.jinshuo.mall.service.user.application.qry.ManagerCountQry;
import com.jinshuo.mall.service.user.service.command.LoginReportComService;
import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dongyh
 * @Classname GoodsOrderQueryService
 * @Description TODO
 * @Date 2019/6/16 19:54
 * @Created by dongyh
 */
@Slf4j
@Service
public class GoodsOrderQueryService {

    @Autowired
    private GoodsOrderRepo goodsOrderRepo;

    @Autowired
    private GoodsOrderDetailRepo goodsOrderDetailRepo;

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    /**
     * 登录状况
     */
    @Autowired
    private LoginReportComService loginReportComService;

    /**
     * 产品查询
     */
    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private GoodsOrderSimpleRepo goodsOrderSimpleRepo;


    /**
     * 根据id查询订单信息
     *
     * @return
     */
    public GoodsOrderListDto findGoodsOrderById(GoodsOrderId goodsOrderId) {
        GoodsOrder goodsOrder = goodsOrderRepo.findGoodsOrderById(goodsOrderId);
        GoodsOrderListDto goodsOrderListDto = GoodsOrderAssembler.assembleGoodsOrderDto(goodsOrder);
        return goodsOrderListDto;
    }

    /**
     * 前端查询订单列表
     *
     * @return
     */
    public PageInfo<GoodsOrderListDto> queryWxOrderList(GoodsOrderQry query) {
        Long userId = UserIdUtils.getUserId();
        //Long userId = UserUtils.getCurrentFrontUserId(jwtSecret);
        query.setMemberId(userId);
        return queryGoodsOrderList(query);
    }

    /**
     * 管理端查询订单列表
     *
     * @return
     */
    public PageInfo<GoodsOrderListDto> queryManagerList(GoodsOrderQry query) {
        return queryGoodsOrderList(query);
    }

    /**
     * 查询订单列表
     *
     * @param query
     * @param query
     * @return
     */
    public PageInfo<GoodsOrderListDto> queryGoodsOrderList(GoodsOrderQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<GoodsOrder> goodsOrderList = goodsOrderRepo.queryGoodsOrderList(query);
        List<GoodsOrderListDto> goodsOrderDtos = new ArrayList<>();
        for (GoodsOrder goodsOrder : goodsOrderList) {
            goodsOrderDtos.add(GoodsOrderAssembler.assembleGoodsOrderDto(goodsOrder));
        }
        PageInfo pageInfo = new PageInfo<>(goodsOrderList);
        pageInfo.setList(goodsOrderDtos);
        return pageInfo;
    }

    /**
     * 查询本人名下每种状态订单的数量
     *
     * @return
     */
    public Map<Integer, Integer> queryMyOrderCountWithStatus() {
        Map<Integer, Integer> map = new HashMap<>();
        Long userId = UserIdUtils.getUserId();
        GoodsOrderQry query = new GoodsOrderQry();
        query.setMemberId(userId);
        List<GoodsOrder> goodsOrderList = goodsOrderRepo.queryGoodsOrderList(query);
        goodsOrderList.forEach(goodsOrder -> {
            Integer num = 1;
            if (null == goodsOrder.getOrderStatus() || null == goodsOrder.getOrderStatus().getCode()) {
                if (map.containsKey(99)) {
                    num = map.get(99) + 1;
                }
                map.put(99, num);
            } else {
                if (map.containsKey(goodsOrder.getOrderStatus().getCode())) {
                    num = map.get(goodsOrder.getOrderStatus().getCode()) + 1;
                }
                map.put(goodsOrder.getOrderStatus().getCode(), num);
            }
        });
        return map;
    }

    /**
     * 汇总供应商业绩
     *
     * @param qry
     * @return
     */
    public PerformanceDto countPerformance(OrderCountQry qry) throws OcBizException {
        log.info(" -- 统计我的产品销售记录 ,输入参数：" + JSONObject.toJSONString(qry));
        qry.setSupplierId(UserIdUtils.getSupplierByUserId().getSupplierId());
        List<OrderDetailQryDto> dtos = goodsOrderDetailRepo.findBySupplierId(qry);
        Map<Integer, List<OrderDetailQryDto>> orderMap = dtos.stream().collect(Collectors.groupingBy(OrderDetailQryDto::getOrderStatus));
        System.out.print(" -- 订单状态 ：" + orderMap.keySet());

       /* BigDecimal noPayAmount = new BigDecimal(0);
        if (orderMap.containsKey(OrderStatusEnums.ORDER_STATUS_UNPAY.code)) {
            noPayAmount = orderMap.get(OrderStatusEnums.ORDER_STATUS_UNPAY.code)
                    .stream()
                    .map(OrderDetailQryDto::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        }*/

        BigDecimal payAmount = new BigDecimal(0);
        List<OrderDetailQryDto> payList = new ArrayList<>();
        if (orderMap.containsKey(OrderStatusEnums.ORDER_STATUS_PAY.code)) {
            payList.addAll(orderMap.get(OrderStatusEnums.ORDER_STATUS_PAY.code));
        }
        if (orderMap.containsKey(OrderStatusEnums.ORDER_STATUS_SHIPPED.code)) {
            payList.addAll(orderMap.get(OrderStatusEnums.ORDER_STATUS_SHIPPED.code));
        }
        if (orderMap.containsKey(OrderStatusEnums.ORDER_STATUS_SIGNED.code)) {
            payList.addAll(orderMap.get(OrderStatusEnums.ORDER_STATUS_SIGNED.code));
        }
        if (orderMap.containsKey(OrderStatusEnums.ORDER_STATUS_FINISHED.code)) {
            payList.addAll(orderMap.get(OrderStatusEnums.ORDER_STATUS_FINISHED.code));
        }
        Integer orderCount = payList.size();
       /* payAmount = payList.stream()
                .map(OrderDetailQryDto::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);*/

        Map<Long, List<OrderDetailQryDto>> spuMap = payList.stream().collect(Collectors.groupingBy(OrderDetailQryDto::getGoodsSpuId));
        Integer spuCount = spuMap.size();

        Map<Long, List<OrderDetailQryDto>> memberMap = payList.stream().collect(Collectors.groupingBy(OrderDetailQryDto::getMemberId));
        Integer memberCount = memberMap.size();

        BigDecimal costPrice = new BigDecimal(0);
        BigDecimal temp = new BigDecimal(0);
        for (OrderDetailQryDto detailQryDto : payList) {
            if (null != detailQryDto.getCostPrice() && null != detailQryDto.getNumber()) {
                temp = detailQryDto.getCostPrice().multiply(new BigDecimal(detailQryDto.getNumber()));
                costPrice = costPrice.add(temp);
            }
        }

        PerformanceDto performanceDto = PerformanceDto.builder()
                .spuCount(spuCount)
                .orderCount(orderCount)
                .memberCount(memberCount)
                .costPrice(costPrice)
                .build();
        return performanceDto;
    }

    /**
     * 小程序查询供应商订单
     *
     * @return
     */
    public PageInfo<GoodsOrderListDto> querySupplierList(GoodsOrderQry query) {
        Long userId = UserIdUtils.getUserId();
        com.jinshuo.mall.service.user.application.dto.SupplierDto supplierDto = userAccountCmdService.getSupplierInfo(userId);
        query.setSupplierId(Long.valueOf(supplierDto.getId()));
        PageInfo<GoodsOrderListDto> page = queryGoodsOrderList(query);
        List<GoodsOrderListDto> dtos = page.getList();
        for (GoodsOrderListDto dto : dtos) {
            BigDecimal costPrice = new BigDecimal(0);
            for (GoodsOrderDetailDto detailDto : dto.getGoodsOrderDetailList()) {
                if (null != detailDto.getNumber() && null != detailDto.getCostPrice()) {
                    costPrice = costPrice.add(detailDto.getCostPrice().multiply(new BigDecimal(detailDto.getNumber())));
                }
            }
            dto.setTotalCostPrice(costPrice);
        }
        page.setList(dtos);
        return page;
    }

    /**
     * 根据id查询订单信息
     *
     * @return
     */
    public GoodsOrderListDto supplierFindGoodsOrderById(GoodsOrderId goodsOrderId) {
        GoodsOrder goodsOrder = goodsOrderRepo.findGoodsOrderById(goodsOrderId);
        GoodsOrderListDto goodsOrderListDto = GoodsOrderAssembler.assembleGoodsOrderDto(goodsOrder);
        BigDecimal costPrice = new BigDecimal(0);
        for (GoodsOrderDetailDto detailDto : goodsOrderListDto.getGoodsOrderDetailList()) {
            if (null != detailDto.getCostPrice() && null != detailDto.getNumber()) {
                costPrice = costPrice.add(detailDto.getCostPrice().multiply(new BigDecimal(detailDto.getNumber())));
            }
        }
        goodsOrderListDto.setTotalCostPrice(costPrice);
        return goodsOrderListDto;
    }

    /**
     * 根据id查询订单信息
     *
     * @return
     */
    public GoodsOrder findOrderById(GoodsOrderId goodsOrderId) {
        return goodsOrderRepo.findGoodsOrderById(goodsOrderId);
    }

    /**
     * 分类统计订单金额
     *
     * @param qry
     */
    public OrderCountInfoDto countOrder(ManagerOrderCountQry qry) {
        log.info(" -- 对账单,输入参数" + JSONObject.toJSONString(qry));
        OrderCountInfoDto dto = new OrderCountInfoDto();
        dto.init();

        List<GoodsOrder> goodsOrders = goodsOrderRepo.countOrder(qry);
        for (GoodsOrder goodsOrder : goodsOrders) {
            dto.addAmount(goodsOrder.getOrderAmountTotal());
            dto.addItemCount(goodsOrder.getGoodsCount());
        }
        dto.setOrderCount(goodsOrders.size());
        try {
            ManagerCountQry managerCountQry = new ManagerCountQry();
            BeanUtils.copyProperties(qry, managerCountQry);
            OrderCountInfoDto dto1 = loginReportComService.queryCount(managerCountQry);
            if (null == dto1) {
                return dto;
            }
            dto.setAccessMan(dto1.getAccessMan());
            dto.setAccessCount(dto1.getAccessCount());
            dto.setAccessMember(dto1.getAccessMember());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 分类统计订单总
     *
     * @param qry
     */
    public OrderCountInfoDto countOrderTotal(ManagerOrderCountQry qry) {
        log.info(" -- 分类统计订单总,输入参数" + JSONObject.toJSONString(qry));
        OrderCountInfoDto dto = new OrderCountInfoDto();
        dto.init();
        List<OrderStatusDto> list = goodsOrderRepo.historyCount(qry.getShopId());
        for (OrderStatusDto orderStatusDto : list) {
            if (OrderStatusEnums.ORDER_STATUS_PAY.code == orderStatusDto.getOrderStatus()) {
                dto.setPay(orderStatusDto.getCount());
            }
            if (OrderStatusEnums.ORDER_STATUS_UNPAY.code == orderStatusDto.getOrderStatus()) {
                dto.setUnpay(orderStatusDto.getCount());
            }
        }
        dto.setSoldOutCount(spuQueryService.getSoldOutCount(qry.getShopId()));
        return dto;
    }

    /**
     * 分类统计订单金额
     *
     * @param qry
     */
    public List<ManagerOrderCountAmountDto> countOrderAmount(ManagerOrderCountAmountQry qry) throws ParseException {
        log.info(" -- 分类统计订单金额,输入参数" + JSONObject.toJSONString(qry));
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.fm_yyyy_MM_dd);
        List<ManagerOrderCountAmountDto> dtos = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        int currYear = DateUtil.getOfYear(new Date());
        int currMonth = DateUtil.getOfMonth(new Date());
        //如果按月统计，则计算出当年的月份
        if (1 == qry.getQryType()) {

            //判断年份。如果是今年则判断过了几个月
            if (qry.getYear() < currYear) {
                for (int i = 12; i > 0; i--) {
                    StringBuffer dateStr = new StringBuffer();
                    dateStr.append(qry.getYear()).append("-").append(i).append("-").append("01");
                    dateList.add(sdf.parse(dateStr.toString()));
                }
            }
            //如果为本年
            else if (Integer.valueOf(qry.getYear()) == currYear) {
                for (int i = currMonth; i > 0; i--) {
                    StringBuffer dateStr = new StringBuffer();
                    dateStr.append(qry.getYear()).append("-").append(i).append("-").append("01");
                    dateList.add(sdf.parse(dateStr.toString()));
                }
            }
        }
        //如果按照月份，则获取每个月多少天
        else if (2 == qry.getQryType()) {
            if (qry.getYear() < currYear) {
                StringBuffer dateStr = new StringBuffer();
                dateStr.append(qry.getYear()).append("-").append(qry.getMonth()).append("-").append("01");
                dateList = DateUtil.getMouthDays(sdf.parse(dateStr.toString()));
            }
            //如果为本年
            else if (Integer.valueOf(qry.getYear()) == currYear) {
                //如果该月已过完
                if (qry.getMonth() < currMonth) {
                    StringBuffer dateStr = new StringBuffer();
                    dateStr.append(qry.getYear()).append("-").append(qry.getMonth()).append("-").append("01");
                    dateList = DateUtil.getMouthDays(sdf.parse(dateStr.toString()));
                }
                //如果为本月
                else if (qry.getMonth() == currMonth) {
                    StringBuffer dateStr = new StringBuffer();
                    dateStr.append(qry.getYear()).append("-").append(qry.getMonth()).append("-").append("01");
                    //查询结束时间
                    dateList = DateUtil.getDateRangeList(sdf.parse(dateStr.toString()), new Date());
                }
            }
        }

        //统计
        for (Date qryDate : dateList) {
            List<BigDecimal> orderAmountTotals = goodsOrderRepo.countOrderAmount(qryDate, qry.getQryType());
            BigDecimal totalIncome = new BigDecimal(0);
            BigDecimal totalPay = new BigDecimal(0);
            for (BigDecimal orderAmountTotal : orderAmountTotals) {
                totalIncome = totalIncome.add(orderAmountTotal);
            }
            ManagerOrderCountAmountDto dto = new ManagerOrderCountAmountDto();
            if (1 == qry.getQryType()) {
                dto.setDate(DateUtil.dateToString(qryDate, "yyyy-MM"));
            } else if (2 == qry.getQryType()) {
                dto.setDate(sdf.format(qryDate));
            }

            dto.setIncome(totalIncome);
            dto.setPay(totalPay);
            dto.setRealIncome(totalIncome.subtract(totalPay));
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * 按照日期分类统计订单
     */
    public PageInfo countOrderByDate(PageQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CountOrderDto> list = goodsOrderRepo.countOrderByDate();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 微信查询样品订单列表
     *
     * @param query
     * @return
     */
    public PageInfo<GoodsSimpleOrderListDto> queryWxSimpleOrderList(GoodsSimpleQry query) {
        Long userId = UserIdUtils.getUserId();
        query.setMemberId(userId);
        return querySimpleOrderList(query);
    }

    /**
     * 查询样品订单列表
     *
     * @param query
     * @return
     */
    public PageInfo<GoodsSimpleOrderListDto> querySimpleOrderList(GoodsSimpleQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<GoodsOrderSimple> goodsOrderList = goodsOrderSimpleRepo.findList(query);
        List<GoodsSimpleOrderListDto> goodsSimpleOrderDtos = new ArrayList<>();
        for (GoodsOrderSimple goodsOrderSimple : goodsOrderList) {
            goodsSimpleOrderDtos.add(GoodsSimpleOrderAssembler.assembleGoodsOrderDto(goodsOrderSimple));
        }
        PageInfo pageInfo = new PageInfo<>(goodsOrderList);
        pageInfo.setList(goodsSimpleOrderDtos);
        return pageInfo;
    }

    /**
     * 查询寄样信息详情
     *
     * @param sampleNo
     * @return
     */
    public GoodsSimpleOrderListDto querySampleOrderDetail(String sampleNo) {
        GoodsOrderSimple goodsOrderSimple = goodsOrderSimpleRepo.findBySampleNo(sampleNo);
        GoodsSimpleOrderListDto goodsSimpleOrderListDto = null;
        if (goodsOrderSimple != null) {
            goodsSimpleOrderListDto = new GoodsSimpleOrderListDto();
            BeanUtils.copyProperties(goodsOrderSimple, goodsSimpleOrderListDto);
        }
        return goodsSimpleOrderListDto;
    }
}
