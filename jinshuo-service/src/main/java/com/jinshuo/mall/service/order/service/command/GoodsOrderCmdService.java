package com.jinshuo.mall.service.order.service.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.exception.order.OcReturnCode;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.UserAuthDto;
import com.jinshuo.core.utils.*;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.order.product.expressCode.ExpressCode;
import com.jinshuo.mall.domain.order.product.order.*;
import com.jinshuo.mall.domain.order.product.order.event.GoodsOrderCreatedMsgEvent;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddressId;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCoupon;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCouponId;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetailId;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimple;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.finance.application.cmd.FinanceRechargeCmd;
import com.jinshuo.mall.service.finance.service.command.FinanceAccountComService;
import com.jinshuo.mall.service.item.application.cmd.CouponLogsCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.TargetCmd;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.dto.SpuOtherDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.command.CouponLogsComService;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.order.application.cmd.*;
import com.jinshuo.mall.service.order.application.constant.IDPrefix;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderIdDto;
import com.jinshuo.mall.service.order.mybatis.*;
import com.jinshuo.mall.service.user.mybatis.MemberRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname GoodsOrderCmdService
 * @Description 产品订单
 * @Date 2019/7/8 15:37
 * @Created by mgh
 * @author mgh
 */
@Slf4j
@Service
public class GoodsOrderCmdService {

    /**
     * appid
     */
    @Value("${wxconfig.app_id}")
    private String appId;


    /**
     * 商户号
     */
    @Value("${wxconfig.mch_id}")
    private String mchId;

    /**
     * 商户秘钥
     */
    @Value("${wxconfig.api_key}")
    private String apiKey;


    /**
     * 回调路径
     */
    @Value("${wxconfig.noncestr}")
    private String noncestr;

    /**
     * 域名
     */
    @Value("${wxconfig.domain}")
    private String domain;

    /**
     * 域名
     */
    @Value("${shortDomain}")
    private String shortDomain;

    /**
     * 域名
     */
    @Value("${checkDetailDomain}")
    private String checkDetailDomain;

    /**
     * 域名
     */
    @Value("${wxconfig.cert_path}")
    private String certPath;


    @Autowired
    private GoodsOrderRepo goodsOrderRepo;

/*    @Autowired
    private GoodsOrderMsgMQ goodsOrderMsgMQ;*/

    @Autowired
    private GoodsExpresssRepo goodsExpresssRepo;

    @Autowired
    private GoodsVerificationCodeRepo goodsVerificationCodeRepo;

    @Autowired
    private ExpresssCodeRepo expresssCodeRepo;

    @Autowired
    private GoodsOrderDetailRepo goodsOrderDetailRepo;

    @Autowired
    private OrderVerificationCodeLogComService orderVerificationCodeLogComService;

    @Autowired
    private CouponLogsComService couponLogsComService;

    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private FinanceAccountComService financeAccountComService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private GoodsOrderSimpleRepo goodsOrderSimpleRepo;


    public String test(){
        /*List<Long> ids = new ArrayList<>();
        ids.add(12345L);
        ids.add(23456L);
        log.info("发送mq====================");
        //发送mq
        goodsOrderMsgMQ.completeProfit(ids,String.valueOf(1*60*1000));*/
        RedisUtil.setex( "test", "test", 1 * 60);
        return "";
    }

    public String delKey(){
        try{
            RedisUtil.del("test");
            return "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
        //clientApi.getByExemple();
    }

    /**
     * 新增订单
     * @param cmd
     */
    @Transactional
    public GoodsOrderIdDto save(GoodsOrderCreateCmd cmd) throws OcBizException {
        log.info("新增订单参数：{}",JSON.toJSONString(cmd));
        //订单id
        Long goodsOrderId = CommonSelfIdGenerator.generateId();

        //获取用户的ID
        UserAuthDto user = UserIdUtils.getUser();

        Long userId = Long.valueOf(user.getId());

        //获取用户的名字
        String userName = user.getNickname();

        //店铺id
        Long shopId = user.getShopId();
        if(shopId==null){
            shopId = 10066L;
        }
        //优惠券金额
        BigDecimal couponAmount =new BigDecimal("0");


        List<TargetCmd> targetCmds = cmd.getGoodsOrderDetailCmdList().stream()
                .map(goodsOrderDetailCmd -> {
                    TargetCmd targetCmd = TargetCmd.builder()
                            .targetId(goodsOrderDetailCmd.getGoodsSkuId())
                            .count(goodsOrderDetailCmd.getNumber())
                            .build();
                    return targetCmd;
                }).collect(Collectors.toList());
        CouponLogsCreateCmd couponLogsCreateCmd = null;
        //检查是否使用优惠券
        if(StringUtils.isNotBlank(cmd.getCouponReceiveId())){
            couponLogsCreateCmd = CouponLogsCreateCmd.builder()
                    .couponReceiveId(Long.valueOf(cmd.getCouponReceiveId()))
                    .memId(userId)
                    .targetCmds(targetCmds)
                    .build();
        }else{
            couponLogsCreateCmd = CouponLogsCreateCmd.builder()
                    .memId(userId)
                    .targetCmds(targetCmds)
                    .build();
        }

        //检查优惠券和库存
        Coupon couponDto = couponLogsComService.checkCoupon(couponLogsCreateCmd);


        //产品详情信息
        List<GoodsOrderDetail> goodsOrderDetailList = cmd.getGoodsOrderDetailCmdList().stream()
                .map(goodsOrderDetailCmd -> {

                    SpuQry spuQry = SpuQry.builder()
                            .spuId(goodsOrderDetailCmd.getGoodsSpuId())
                            .skuId(goodsOrderDetailCmd.getGoodsSkuId())
                            .build();

                    SpuDto spuDto = spuQueryService.findByExemple(spuQry);

                    SpuOtherDto spuOtherDto =spuDto.getSpuOtherDto();
                    //运费
                    BigDecimal logisticsFee = null;
                    if(spuOtherDto==null){
                        logisticsFee = new BigDecimal("0");
                    }else{
                        logisticsFee = spuOtherDto.getCourierFee();
                    }

                    GoodsOrderDetail goodsOrderDetail  = new GoodsOrderDetail().save(
                            new GoodsOrderDetailId(CommonSelfIdGenerator.generateId()),
                            //供应商Id
                            spuDto.getSupplierId()==null? 1688: Long.valueOf(spuDto.getSupplierId()),
                            //供应商名称
                            spuDto.getSupplierName(),
                            //成本价
                            spuDto.getCostPrice(),
                            //运费
                            logisticsFee,
                            //订单id
                            new GoodsOrderId(goodsOrderId),
                            //产品spuId
                            goodsOrderDetailCmd.getGoodsSpuId(),
                            //产品名称（skuId）
                            spuDto.getName(),
                            //产品skuName
                            spuDto.getSkus().get(0).getName(),
                            //获取产品封面图
                            spuDto.getPictureUrl(),
                            //市场价
                            spuDto.getSkus().get(0).getPrice(),
                            //产品skuId
                            goodsOrderDetailCmd.getGoodsSkuId(),
                            //折扣
                            goodsOrderDetailCmd.getDiscountRate(),
                            //折扣金额
                            goodsOrderDetailCmd.getDiscountAmount(),
                            //购买数量
                            goodsOrderDetailCmd.getNumber(),
                            //备注
                            goodsOrderDetailCmd.getRemarks(),
                            //是否发码产品
                            spuDto.getIsSendcode(),
                            //发码地址
                            spuDto.getReserveAddress(),
                            spuDto.getAutoSendCode()
                    );
            return goodsOrderDetail;
        }).collect(Collectors.toList());

        GoodsOrderAddress goodsOrderAddress = null;
        if(cmd.getGoodsOrderAddressCmd()!=null){
            //地址信息
            goodsOrderAddress =new GoodsOrderAddress().save( new GoodsOrderAddressId(CommonSelfIdGenerator.generateId()),
                            new GoodsOrderId(goodsOrderId),
                            cmd.getGoodsOrderAddressCmd().getUserName(),
                            cmd.getGoodsOrderAddressCmd().getUserAddress(),
                            cmd.getGoodsOrderAddressCmd().getUserPhone()
                    );
        }


        //商品总价
        BigDecimal goodsAmountTotal =goodsOrderDetailList.stream().map(GoodsOrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("商品总价为："+goodsAmountTotal);

        //运费获取最大值
        BigDecimal logisticsFee =goodsOrderDetailList.stream().map(GoodsOrderDetail::getLogisticsFee).reduce(BigDecimal.ZERO, BigDecimal::max);
        log.info("商品的最大运费为:"+logisticsFee);

        //订单号
        String orderNo = IDPrefix.YM_ORDER_PREFIX+ String.valueOf(System.currentTimeMillis());

        List<GoodsOrderCoupon> goodsOrderCouponList = new ArrayList<GoodsOrderCoupon>();
        //是否有使用优惠券
        if(StringUtils.isNotBlank(cmd.getCouponReceiveId())){
            /*List<GoodsOrderDetailCmd> cmdList = cmd.getGoodsOrderDetailCmdList();
            List<TargetCmd> targetCmds = cmdList.stream()
                    .map(goodsOrderDetailCmd -> {
                        TargetCmd targetCmd = TargetCmd.builder()
                                .targetId(goodsOrderDetailCmd.getGoodsSkuId())
                                .count(goodsOrderDetailCmd.getNumber())
                                .build();
                        return targetCmd;
                    }).collect(Collectors.toList());

            CouponLogsCreateCmd couponLogsCreateCmd = CouponLogsCreateCmd.builder()
                    .couponReceiveId(Long.valueOf(cmd.getCouponReceiveId()))
                    .memId(userId)
                    .targetCmds(targetCmds)
                    .build();

            CouponDto couponDto = itemServiceResponse.checkCoupon(couponLogsCreateCmd);*/

            //有优惠券获取优惠券金额
            couponAmount = couponAmount.add(couponDto.getCouponAmount());

            //保存优惠券信息
            GoodsOrderCoupon goodsOrderCoupon = GoodsOrderCoupon.builder()
                    .goodsOrderCouponId(new GoodsOrderCouponId(CommonSelfIdGenerator.generateId()))
                    .goodsOrderId(new GoodsOrderId(goodsOrderId))
                    .couponAmount(couponAmount)
                    .couponReceiveId(Long.valueOf(cmd.getCouponReceiveId()))
                    .name(couponDto.getName())
                    .build();
            goodsOrderCouponList.add(goodsOrderCoupon);


            //测试使用优惠券
            /*CouponLogsCreateCmd couponLogsCreateCmd2 = CouponLogsCreateCmd.builder()
                    .couponReceiveId(Long.valueOf(cmd.getCouponReceiveId()))
                    .orderId(goodsOrderId)
                    .targetCmds(targetCmds)
                    .build();
            itemServiceResponse.useCoupon(couponLogsCreateCmd2);*/
        }

        //实际支付金额(产品金额+运费金额-优惠金额) 如果大于0则显示金额，小于0 则为0
        BigDecimal orderAmountTotal = goodsAmountTotal.add(logisticsFee).subtract(couponAmount);
        if(orderAmountTotal.compareTo(new BigDecimal(0))==1){
            orderAmountTotal  = goodsAmountTotal.add(logisticsFee).subtract(couponAmount);
        }else{
            orderAmountTotal = new BigDecimal(0);
        }

        GoodsOrder goodsOrder = GoodsOrder.builder()
                .goodsOrderId(new GoodsOrderId(goodsOrderId))
                //订单号
                .orderNo(orderNo)
                .orderType(cmd.getOrderType())
                .memberId(userId)
                .memberName(userName)
                //.memberName(URLDecoder.decode(userName, "utf-8"))
                .supplierId(cmd.getSupplierId())
                .shopId(shopId==null?10088L : shopId)
                //待支付
                .orderStatus(OrderStatusEnums.ORDER_STATUS_UNPAY)
                .afterStatus(0)
                .goodsCount(goodsOrderDetailList.size())
                .goodsAmountTotal(goodsAmountTotal)
                //运费金额
                .logisticsFee(logisticsFee==null? new BigDecimal(0):logisticsFee)
                .couponAmount(couponAmount)
                //实际付款金额
                .orderAmountTotal(orderAmountTotal)
                //默认微信支付
                .payChannel(PayChannelEnums.ORDER_PAY_CHANNEL_WECHAT)
                //默认为正常订单
                .orderClass(0)
                //第三方支付流水号
               /* .escrowTradeNo()
                //支付时间
                .payTime()
                //发货时间
                .deliveryTime()*/
                //结算状态
                .settlementStatus(SettlementStatusEnums.ORDER_SETTLEMENT_STATUS_NO)
                //支付状态
                .payStatus(OrderPayStatusEnums.ORDER_PAY_STATUS_UNPAY)
                .isIntegral(1)
                .goodsOrderDetailList(goodsOrderDetailList)
                .goodsOrderCouponList(goodsOrderCouponList)
                .goodsOrderAddress(goodsOrderAddress)
                .build();
        goodsOrder.preInsert();
        if(StringUtils.isNotBlank(cmd.getRemarks())){
            goodsOrder.setRemarks(cmd.getRemarks());
        }
        goodsOrderRepo.save(goodsOrder);

        //添加支付超时
        /*goodsOrderMsgMQ.sendCreateOrderOverTime(
                GoodsOrderOverTimeEvent.builder()
                        //设置订单号
                        .orderId(String.valueOf(goodsOrderId))
                        .build()
        );*/

        //返回前端所需数据
        GoodsOrderIdDto  goodsOrderIdDto = new GoodsOrderIdDto();
        goodsOrderIdDto.setId(goodsOrderId);
        goodsOrderIdDto.setOrderNo(orderNo);

        return goodsOrderIdDto;
    }


    /**
     * 取消订单
     * @param goodsOrderUpdateStatusCmd
     */
    public void cancelOrder(GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd) {
        //构建取消对象
        GoodsOrder goodsOrder = new GoodsOrder().cancelOrder(goodsOrderUpdateStatusCmd.getId(),"1");
        goodsOrderRepo.update(goodsOrder);
    }


    /**
     * 更新订单状态
     * @param goodsOrderUpdateStatusCmd
     */
    public void updateStatus(GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd) {
        //构建更新对象
        GoodsOrder goodsOrder = new GoodsOrder().updateStatus(
                goodsOrderUpdateStatusCmd.getId(),
                goodsOrderUpdateStatusCmd.getOrderStatus());
        goodsOrderRepo.update(goodsOrder);
    }

    /**
     * 删除订单
     * @param goodsOrderUpdateStatusCmd
     */
    public void delete(GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd) {
        GoodsOrder goodsOrder = new GoodsOrder().delete(goodsOrderUpdateStatusCmd.getId());
        goodsOrderRepo.update(goodsOrder);
    }

    /**
     * 更新系统备注
     * @param goodsOrderUpdateStatusCmd
     */
    public void updateSystemRemarks(GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd) {
        GoodsOrder goodsOrder = new GoodsOrder().updateSysRemarks(goodsOrderUpdateStatusCmd.getId(),goodsOrderUpdateStatusCmd.getSystemRemarks());
        goodsOrderRepo.update(goodsOrder);
    }


    /**
     * 更新快递信息
     * @param goodsExpressCmd
     */
    @Transactional
    public void upExpress(GoodsExpressCmd goodsExpressCmd) throws OcBizException {
        //查询原订单
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderById(new GoodsOrderId(goodsExpressCmd.getOrderId()));
        //查询快递编码
        ExpressCode expressCode = expresssCodeRepo.findExpressByName(goodsExpressCmd.getExpressCompanyName());

        GoodsOrder goodsOrder = GoodsOrder.builder()
                .goodsOrderId(new GoodsOrderId(goodsExpressCmd.getOrderId()))
                .orderStatus(OrderStatusEnums.ORDER_STATUS_SHIPPED)
                .build();
        GoodsOrderExpress goodsOrderExpress = GoodsOrderExpress.builder()
                .goodsOrderExpressId(goodsExpresssRepo.nextId())
                .goodsOrderId(new GoodsOrderId(goodsExpressCmd.getOrderId()))
                .expressCompanyName(goodsExpressCmd.getExpressCompanyName())
                .expressNo(goodsExpressCmd.getExpressNo())
                .expressCode(expressCode.getExpressCode())
                .goodsOrder(goodsOrder)
                .createDate(new Date())
                .build();
        goodsExpresssRepo.save(goodsOrderExpress);


    }

    /**
     * 完成订单
     * @param goodsFinshedCmd
     */
    public void finished(GoodsFinshedCmd goodsFinshedCmd){
        /*goodsOrderRepo.finshed(goodsFinshedCmd.getIds());
        BillingRuleDto billingRuleDto = distributionServiceResponse.getUpgrdeRule(goodsFinshedCmd.getShopId());
        log.info("获取自动延期的天数为：{}",billingRuleDto.getOrderSettleDays());
        //发送mq
        goodsOrderMsgMQ.completeProfit(goodsFinshedCmd.getIds(),String.valueOf(billingRuleDto.getOrderSettleDays()*24*60*60*1000));*/
    }


    /**
     * 更新订单验证码信息
     * @param goodsVerificationCodeCmd
     */
    @Transactional
    public void upVerificationCode(GoodsVerificationCodeCmd goodsVerificationCodeCmd) throws OcBizException {
        //查询原订单
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderById(new GoodsOrderId(goodsVerificationCodeCmd.getOrderId()));

        GoodsOrder goodsOrder = GoodsOrder.builder()
                .goodsOrderId(new GoodsOrderId(goodsVerificationCodeCmd.getOrderId()))
                .orderStatus(OrderStatusEnums.ORDER_STATUS_SHIPPED)
                .build();
        goodsOrderRepo.update(goodsOrder);


        StringBuffer verifySn=new StringBuffer();
        //保存验证码列表
        for (VerificationCodeCmd verificationCodeCmd :goodsVerificationCodeCmd.getVerificationCodeList()){
            String [] verficationcode = verificationCodeCmd.getVerifySn().split(",");
            for(String vc : verficationcode){
                if(StringUtils.isNotBlank(vc)){
                    OrderVerificationCode orderVerificationCode = OrderVerificationCode.builder()
                            .orderVerificationCodeId(goodsVerificationCodeRepo.nextId())
                            .goodsOrderId(new GoodsOrderId(goodsVerificationCodeCmd.getOrderId()))
                            .goodsOrderDetailId(new GoodsOrderDetailId(verificationCodeCmd.getOrderDetailId()))
                            .verifySn(verificationCodeCmd.getVerifySn())
                            .isUse(1)
                            .build();
                    orderVerificationCode.preInsert();
                    goodsVerificationCodeRepo.save(orderVerificationCode);
                }
            }
            //验证码以逗号分隔
            verifySn = verifySn.append(verificationCodeCmd.getVerifySn()).append(",");
        }
    }


    /**
     * 微信支付
     * @param goodsOrderPayCmd
     * @return
     */
    /*public Map<String, String> wechat(GoodsOrderPayCmd goodsOrderPayCmd) throws OcBizException {
        log.info("微信支付接收字符串为："+ JSON.toJSONString(goodsOrderPayCmd));
        Map<String, String> returnMap =null;
        //获取订单信息
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderById(new GoodsOrderId(Long.valueOf(goodsOrderPayCmd.getId())));

        if(goodsOrderSearch == null){
            throw new OcBizException(OcReturnCode.OC202005.getMsg(),OcReturnCode.OC202005.getCode());
        }

        //订单号
        String orderNo = goodsOrderSearch.getOrderNo();
        //支付方式
        WxPayApi.TradeType tradeType = WxPayApi.TradeType.APP;

        log.info("当前支付的ip为："+ HttpUtils.getIpAddress());
        String productName = goodsOrderSearch.getGoodsOrderDetailList().get(0).getGoodsName();
        log.info("产品长度为："+ productName.length());
        if(productName.length()>128){
            productName = productName.substring(0,120);
        }

        //支付订单
        PayParm parm = PayParm.builder()
                .appId(appId)
                .mchId(mchId)
                .apiKey(apiKey)
                //支付方式
                .tradeType(tradeType)
                .body("朗纱汇-"+productName)
                .nonceStr(noncestr)
                //支付单号
                .orderCode(orderNo)
                //支付总金额
                .totalFee(String.valueOf(goodsOrderSearch.getOrderAmountTotal().multiply(new BigDecimal(100)).intValue()))
                .attach("")
                .send_url("")
                .spbillCreateIp(HttpUtils.getIpAddress())
                .build();

        try {
            returnMap = WeiXinPay.pay(parm);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JDOMException e) {
            e.printStackTrace();
        }
        return  returnMap;
    }*/




    /**
     * 应用appid
     */
    @Value("${alipayconfig.APP_ID}")
    private String APP_ID;

    /**
     * 私钥
     */
    @Value("${alipayconfig.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY;

    /**
     * 公钥
     */
    @Value("${alipayconfig.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;

    /**
     * 公钥
     */
    @Value("${alipayconfig.CHARSET}")
    private String CHARSET;

    /**
     * 公钥
     */
    @Value("${alipayconfig.NOTIFY_URL}")
    private String NOTIFY_URL;

    /**
     * 支付宝支付
     * @param goodsOrderPayCmd
     * @return
     */
    /*public AlipayTradeAppPayResponse alipay(GoodsOrderPayCmd goodsOrderPayCmd) throws OcBizException {
        //查询原订单
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderById(new GoodsOrderId(Long.valueOf(goodsOrderPayCmd.getId())));
        if(goodsOrderSearch == null){
            throw new OcBizException(OcReturnCode.OC202005.getMsg(),OcReturnCode.OC202005.getCode());
        }

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("");
        //默认名称为第一个
        model.setSubject(goodsOrderSearch.getGoodsOrderDetailList().get(0).getGoodsName());
        model.setOutTradeNo(goodsOrderSearch.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(goodsOrderSearch.getOrderAmountTotal().toString());
        request.setBizModel(model);
        //商户外网可以访问的异步地址
        request.setNotifyUrl(NOTIFY_URL);
        AlipayTradeAppPayResponse response = null;
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            if(response.isSuccess()){
                log.info("调用成功");
            }else{
                log.info("调用失败");
            }
            log.info("app支付返回参数：{}", JSON.toJSONString(response));
            log.info("app支付返回参数body：{}",response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("支付错误：{}",e.getErrMsg());
            throw new OcBizException(e.getErrMsg(),OcReturnCode.OC202005.getCode());
        }
        return  response;
    }*/

    /**
     * 支付宝退款
     * @param
     * @return
     */
    /*public void alipayRefund() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",APP_ID,APP_PRIVATE_KEY,"json",CHARSET,ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
        refundModel.setTradeNo("11111");
        refundModel.setRefundAmount("0.01");
        refundModel.setRefundReason("商品退款");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(refundModel);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        log.info("支付宝退款返回信息为：{}",response.getMsg());
        log.info("支付宝退款返回信息体为：{}",response.getBody());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }*/


    /**
     * 支付宝异步调用方法
     * @param request
     * @return
     */
    /*public String ayncnotify(AyncNotify ayncNotify, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        log.info("Alipay aync notify: {}", ayncNotify);
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("支付宝返回的参数集合为：{}",JSON.toJSONString(params));

        boolean flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET,"RSA2");

        // 验证签名
        if (flag) {
            if(ayncNotify.getTrade_status().equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知

            } else if (ayncNotify.getTrade_status().equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                String orderNo = ayncNotify.getTrade_no();
                //支付宝外部单号
                String outTradeNo = ayncNotify.getOut_trade_no();
                payAfterAction(orderNo,outTradeNo,2,PayChannelEnums.ORDER_PAY_CHANNEL_ALIPAY);
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            log.info("Verify aync notify success!");
            return "success";
        } else {
            log.error("Verify aync notify fail!");
            return "fail";
        }
    }
*/

    /**
     * 余额支付
     * @param goodsOrderPayCmd
     * @return
     * @throws OcBizException
     */
    public void balance(GoodsOrderPayCmd goodsOrderPayCmd){

        //查询原订单
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderById(new GoodsOrderId(Long.valueOf(goodsOrderPayCmd.getId())));
        if(goodsOrderSearch == null){
            throw new OcBizException(OcReturnCode.OC202005.getMsg(),OcReturnCode.OC202005.getCode());
        }

        //判断订单是否已经支付
        if(!goodsOrderSearch.getOrderStatus().equals(OrderStatusEnums.ORDER_STATUS_UNPAY)){
            throw new OcBizException(OcReturnCode.OC202011.getMsg(),OcReturnCode.OC202011.getCode());
        }

         Long userId = UserIdUtils.getUserId();
        //Long userId = 190885212542992384L;
        Member member = memberRepo.queryByUserId(userId);

        if(member == null){
            throw new UcBizException(UcReturnCode.UC200021.getMsg());
        }
        //判断是否设置支付密码
        if(StringUtils.isBlank(member.getPayPassword())){
            throw new UcBizException(UcReturnCode.UC200041.getMsg(),UcReturnCode.UC200041.getCode());
        }
        //判断交易密码是否正确
        Boolean flag = BPwdEncoderUtils.matches(goodsOrderPayCmd.getPassword(),member.getPayPassword());
        if(!flag){
            throw new UcBizException(UcReturnCode.UC200040.getMsg(),UcReturnCode.UC200040.getCode());
        }
        BPwdEncoderUtils.matches(goodsOrderPayCmd.getPassword(),member.getPayPassword());

        payAfterAction(goodsOrderSearch.getOrderNo(),goodsOrderSearch.getOrderNo(),1,PayChannelEnums.ORDER_PAY_CHANNEL_CASH);
    }





    /**
     * 校验优惠券和库存信息
     * @param goodsOrderSearch
     * @param userId
     * @return
     * @throws OcBizException
     */
    private CouponLogsCreateCmd checkCoupon(GoodsOrder goodsOrderSearch, Long userId) throws OcBizException {
        List<GoodsOrderDetail> cmdList = goodsOrderSearch.getGoodsOrderDetailList();

        List<TargetCmd> targetCmds = cmdList.stream()
                .map(goodsOrderDetailCmd -> {
                    TargetCmd targetCmd = TargetCmd.builder()
                            .targetId(goodsOrderDetailCmd.getGoodsSkuId())
                            .count(goodsOrderDetailCmd.getNumber())
                            .build();
                    return targetCmd;
                }).collect(Collectors.toList());


        CouponLogsCreateCmd couponLogsCreateCmd = null;
        //检查是否使用优惠券
        if(goodsOrderSearch.getGoodsOrderCouponList().size()>0){
            couponLogsCreateCmd = CouponLogsCreateCmd.builder()
                    .orderId(goodsOrderSearch.getGoodsOrderId().getId())
                    .couponReceiveId(goodsOrderSearch.getGoodsOrderCouponList().get(0).getCouponReceiveId())
                    .memId(userId)
                    .targetCmds(targetCmds)
                    .build();
        }else{
            couponLogsCreateCmd = CouponLogsCreateCmd.builder()
                    .orderId(goodsOrderSearch.getGoodsOrderId().getId())
                    .memId(userId)
                    .targetCmds(targetCmds)
                    .build();
        }
        return couponLogsCreateCmd;
    }


    /**
     * 微信支付完成回调函数
     * @param request
     * @param response
     * @throws Exception
     */
    /*public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("PayController(notify)进入微信回调函数");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = XMLUtil.doXMLParse(sb.toString());

        //过滤空 设置 TreeMap
        Map<String, String> packageParams = new HashMap<String, String>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        //交易的系统订单号
        String orderNo = packageParams.get("out_trade_no");
        //查询订单
        GoodsOrder goodsOrder = GoodsOrder.builder().orderNo(orderNo).build();
        GoodsOrder goodsOrderSearch = goodsOrderRepo.findGoodsOrderByOrderNo(goodsOrder);
        String resXml = "";
        log.info("当前订单状态为：{},是否相等{}",goodsOrderSearch.getOrderStatus().code,!OrderStatusEnums.ORDER_STATUS_UNPAY.code.equals(goodsOrderSearch.getOrderStatus().code));
        if(!OrderStatusEnums.ORDER_STATUS_UNPAY.code.equals(goodsOrderSearch.getOrderStatus().code)){
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            response.setContentType("text/xml; charset=utf-8");
            response.getWriter().write(resXml);
        }else{
            if(PaymentKit.verifyNotify(packageParams,apiKey)) {
                    //------------------------------
                    //处理业务开始
                    //------------------------------
                    if("SUCCESS".equals(packageParams.get("result_code"))){

                        //微信支付的订单流水号
                        String transactionId = packageParams.get("transaction_id");

                        payAfterAction(goodsOrderSearch.getOrderNo(),transactionId,3,PayChannelEnums.ORDER_PAY_CHANNEL_WECHAT);

                        //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                                + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                    //------------------------------
                    //处理业务完毕
                    //------------------------------
                    response.setContentType("text/xml; charset=utf-8");
                    response.getWriter().write(resXml);
                } else{
                    log.info("通知签名验证失败");
                }
            }
    }*/


    /**
     * 支付完成之后处理方法
     * @param orderNo 订单号
     * @param outTradeNo 第三方平台流水号
     * @param type 支付渠道 1余额支出 2：支付宝支付 3：微信支付
     * @param payChannelEnums 支付渠道
     */
    private void payAfterAction(String orderNo,String outTradeNo,Integer type,PayChannelEnums payChannelEnums) {
        GoodsOrder gos = GoodsOrder.builder()
                .orderNo(orderNo)
                .build();
        GoodsOrder goodsOrder = goodsOrderRepo.findGoodsOrderByOrderNo(gos);
        //如果订单为未支付则进行更新
        if(goodsOrder.getPayStatus().equals(OrderPayStatusEnums.ORDER_PAY_STATUS_UNPAY)){
            goodsOrder.setPayChannel(payChannelEnums);
            goodsOrder.setOrderStatus(OrderStatusEnums.ORDER_STATUS_PAY);
            //设置支付状态为已支付
            goodsOrder.setPayStatus(OrderPayStatusEnums.ORDER_PAY_STATUS_PAY);
            //第三方平台流水号
            goodsOrder.setOutTradeNo(outTradeNo);
            goodsOrder.setPayTime(new Date());
            goodsOrderRepo.update(goodsOrder);
        }
        //扣除库存
        //减库存并且在有优惠券的情况下使用优惠券
        CouponLogsCreateCmd couponLogsCreateCmd  = checkCoupon(goodsOrder, goodsOrder.getMemberId());
        try{
            couponLogsComService.create(couponLogsCreateCmd);
        }catch (Exception e){
            e.printStackTrace();
        }


        //消费记录，如果是余额直接减少
        FinanceRechargeCmd cmd = new FinanceRechargeCmd();
        cmd.setAmount(goodsOrder.getOrderAmountTotal());
        cmd.setMemberId(gos.getMemberId());
        cmd.setType(type);
        cmd.setSn(goodsOrder.getOrderNo());
        try{
            financeAccountComService.consumption(cmd);
        }catch (FcBizException e){
            log.error("资金账户扣除失败：{}",e.getMessage());
        }
    }


    /**
     * 查询快递信息
     * @param goodsOrderIdCmd
     * @return
     * @throws Exception
     */
    public JSONObject expressSchedule(GoodsOrderIdCmd goodsOrderIdCmd) throws Exception {
        GoodsOrderId goodsOrderId = new GoodsOrderId();
        goodsOrderId.setId(goodsOrderIdCmd.getId());
        GoodsOrderExpress goodsOrderExpress = goodsExpresssRepo.findGoodsExpressById(goodsOrderId);
        if(goodsOrderExpress == null){
            throw new OcBizException(OcReturnCode.OC202003.getMsg(),OcReturnCode.OC202003.getCode());
        }
        return null;
        /*return KdniaoTrackQueryAPI.getOrderTracesByJson(goodsOrderExpress.getExpressCode(),
                goodsOrderExpress.getExpressNo(),false);*/
    }


    /**
     * 测试发码
     *
     * @param orderNo
     * @throws OcBizException
     */
    public GoodsVerificationCodeCmd testSendCode(String orderNo) throws OcBizException {
        GoodsOrder temp = GoodsOrder.builder().orderNo(orderNo).build();
        GoodsOrder goodsOrder = goodsOrderRepo.findGoodsOrderByOrderNo(temp);
        return autoSendCode(goodsOrder);
    }

    /**
     * 自主发码
     *
     * @param goodsOrder
     */
    public GoodsVerificationCodeCmd autoSendCode(GoodsOrder goodsOrder) throws OcBizException {
        log.info(" -- 开始自主发码，输入参数：" + JSONObject.toJSONString(goodsOrder));
        List<GoodsOrderDetail> details = goodsOrderDetailRepo.findByOrderId(goodsOrder.getGoodsOrderId());
        GoodsVerificationCodeCmd goodsVerificationCodeCmd = new GoodsVerificationCodeCmd();
        goodsVerificationCodeCmd.setOrderId(goodsOrder.getGoodsOrderId().getId());
        List<VerificationCodeCmd> verificationCodeList = new ArrayList<>();
        VerificationCodeCmd verificationCodeCmd;
        Integer number;
        for (GoodsOrderDetail detail : details) {
            if (null != detail.getAutoSendCode() && 0 == detail.getAutoSendCode()) {
                SpuQry spuQry = SpuQry.builder()
                        .spuId(detail.getGoodsSpuId())
                        .skuId(detail.getGoodsSkuId())
                        .build();
                SpuDto spuDto = spuQueryService.findByExemple(spuQry);
                SpuOtherDto spuOtherDto =spuDto.getSpuOtherDto();
                String shortName = spuDto.getSketch();
                log.info("获取到的简短名称为：{}",shortName);
                int num = detail.getNumber();
                String date = DateUtil.dateToString(spuOtherDto.getActivityStartDate(),DateUtil.cn_yyyyMMdd)+"-"+
                        DateUtil.dateToString(spuOtherDto.getActivityEndDate(),DateUtil.cn_yyyyMMdd);
                //发送短信
                sendCodeMessage(goodsOrder,shortName,String.valueOf(num),date);
                for (int i = 0; i < num; i++) {
                    verificationCodeCmd = new VerificationCodeCmd();
                    verificationCodeCmd.setOrderDetailId(detail.getGoodsOrderDetailId().getId());
                    verificationCodeCmd.setVerifySn(VerificationCodeUtils.getCode());
                    verificationCodeList.add(verificationCodeCmd);
                }
            }
        }
        if (null == verificationCodeList || verificationCodeList.size() < 1) {
            return goodsVerificationCodeCmd;
        }
        goodsVerificationCodeCmd.setVerificationCodeList(verificationCodeList);
        log.info(" -- 结束自主发码，返回参数：" + JSONObject.toJSONString(goodsVerificationCodeCmd));
        goodsOrderRepo.updateOrderStatus(goodsOrder.getGoodsOrderId().getId(),OrderStatusEnums.ORDER_STATUS_SHIPPED.getCode());
        upVerificationCode(goodsVerificationCodeCmd);
        return goodsVerificationCodeCmd;
    }

    /**
     *   发送核销二维码短信
     * @param goodsOrder
     * @param productName
     * @param num
     */
    private void sendCodeMessage(GoodsOrder goodsOrder,String productName,String num,String date) {
        String username = goodsOrder.getGoodsOrderAddress().getUserName();
        String mobile = goodsOrder.getGoodsOrderAddress().getUserPhone();
        String url  = checkDetailDomain+"/#/pages/order/detail?orderId="+goodsOrder.getGoodsOrderId().getId();
        String shortUrl = null;
        //自主发码产品发送短信
        try {
            Map<String,String> reqeustParm = new HashMap<>();
            reqeustParm.put("url",url);
            JSONObject jsonObject = HttpsClientUtil.doGet(shortDomain,reqeustParm);
            Boolean success = jsonObject.getBoolean("success");
            if(success){
                shortUrl = jsonObject.getString("shortUrl");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //短信信息
        String content = GoodsOrderCreatedMsgEvent.sendCodeContentMsg.replaceFirst("\\{name\\}",username)
                .replaceFirst("\\{productName\\}",productName)
                .replaceFirst("\\{num\\}",num)
                .replaceFirst("\\{url\\}",shortUrl)
                .replaceFirst("\\{date\\}",date);

        //发送短息
       /* goodsOrderMsgMQ.sendCreateOrderMsg(
                 GoodsOrderCreatedMsgEvent.builder()
                         .name(username)
                         .mobile(mobile)
                         .content(content)
                         .build()
         );*/
    }


    /**
     * 核销卷码
     *
     * @param cmd
     * @throws OcBizException
     */
    @Transactional
    public OrderVerificationCode writeOffCode(WriteOffVerificationCodeCmd cmd) throws OcBizException {
        log.info(" -- 核销产品卷,输入参数：" + JSONObject.toJSONString(cmd));
        cmd.setSupplierId(UserIdUtils.getSupplierByUserId().getSupplierId());
        OrderVerificationCode orderVerificationCode = null;
        GoodsOrderDetail goodsOrderDetail = null;
        Integer errCode = 0;
        String errMsg = "";
        try {
            orderVerificationCode = goodsVerificationCodeRepo.findByverifySn(cmd.getVerifySn());
            if (null == orderVerificationCode) {
                throw new OcBizException(OcReturnCode.OC202004.getMsg());
            }
            goodsOrderDetail = goodsOrderDetailRepo.findById(orderVerificationCode.getGoodsOrderDetailId().getId());
            if (0 == orderVerificationCode.getIsUse()) {
                throw new OcBizException(OcReturnCode.OC202007.getMsg());
            }
            if (null == goodsOrderDetail) {
                throw new OcBizException(OcReturnCode.OC202005.getMsg());
            }
            if (goodsOrderDetail.getSupplierId().longValue() != cmd.getSupplierId().longValue()) {
                throw new OcBizException(OcReturnCode.OC202006.getMsg());
            }
            goodsVerificationCodeRepo.useVerificationCode(orderVerificationCode.getOrderVerificationCodeId().getId());
            List<OrderVerificationCode> codes = goodsVerificationCodeRepo.findOrderId(goodsOrderDetail.getGoodsOrderId().getId());
            Boolean isComplete = true;
            for (OrderVerificationCode code : codes) {
                if (null == code.getIsUse() || 1 == code.getIsUse()) {
                    if (!cmd.getVerifySn().equals(code.getVerifySn())) {
                        isComplete = false;
                    }
                }
            }
            if (isComplete) {
                goodsOrderRepo.updateOrderStatus(goodsOrderDetail.getGoodsOrderId().getId(), OrderStatusEnums.ORDER_STATUS_FINISHED.getCode());
            }
        } catch (OcBizException e) {
            errCode = -1;
            errMsg = e.getRetMsg();
            e.printStackTrace();
            log.error("核销产品失败", e);
        } catch (Exception e) {
            errCode = -1;
            errMsg = "系统异常！";
            log.error("核销产品失败", e);
        } finally {
            if(null==orderVerificationCode){
                orderVerificationCode = OrderVerificationCode.builder().verifySn(cmd.getVerifySn()).build();
            }
            orderVerificationCodeLogComService.recording(orderVerificationCode, goodsOrderDetail, errCode, errMsg);
        }
        if (StringUtils.isNotBlank(errMsg)) {
            throw new OcBizException(errMsg,errCode);
        }
        return orderVerificationCode;
    }


    /**
     * 微信退款
     * @param wxRefundCmd
     */
    public  Map<String, String> refund(WxRefundCmd wxRefundCmd) throws OcBizException {
        GoodsOrder gob = GoodsOrder.builder()
                .orderNo(wxRefundCmd.getOrderNo())
                .build();
        GoodsOrder goodsOrder = goodsOrderRepo.findGoodsOrderByOrderNo(gob);
        if(goodsOrder == null){
            throw new OcBizException(OcReturnCode.OC202005.getMsg(),OcReturnCode.OC202005.getCode());
        }
        return null;
    }

    /**
     * 更新订单状态
     *
     * @param status
     * @return
     */
    public void updateOrderStatus(Long orderId, Integer status) {
        goodsOrderRepo.updateOrderStatus(orderId, status);
    }


    /**
     * 新增寄样信息
     * @param goodsOrderSimpleCmd
     * @throws OcBizException
     */
    public void addSimpleOrder(GoodsOrderSimpleCmd goodsOrderSimpleCmd
    ) throws OcBizException {
        Long userId = UserIdUtils.getUserId();
        Long shopId = goodsOrderSimpleCmd.getShopId();
        String sampleNo = String.valueOf("SO"+System.currentTimeMillis());
        GoodsOrderSimple goodsOrderSimple =new GoodsOrderSimple().save(
                goodsOrderSimpleRepo.nextId(),
                sampleNo,
                userId,
                shopId,
                goodsOrderSimpleCmd.getSimpleInfo(),
                goodsOrderSimpleCmd.getUserName(),
                goodsOrderSimpleCmd.getUserAddress(),
                goodsOrderSimpleCmd.getUserPhone()
        );
        goodsOrderSimpleRepo.save(goodsOrderSimple);
    }

    /**
     * 更新快递信息
     * @param goodsExpressCmd
     */
    public void upSampleOrderExpress(GoodsExpressCmd goodsExpressCmd) throws OcBizException {
        GoodsOrderSimple goodsOrderSimple = goodsOrderSimpleRepo.findById(goodsExpressCmd.getOrderId());
        //查询快递编码
        ExpressCode expressCode = expresssCodeRepo.findExpressByName(goodsExpressCmd.getExpressCompanyName());

        if(goodsOrderSimple!=null && expressCode!=null){
            goodsOrderSimple.setExpressNo(goodsExpressCmd.getExpressNo());
            goodsOrderSimple.setExpressCompany(goodsExpressCmd.getExpressCompanyName());
            goodsOrderSimple.setExpressDate(new Date());
            goodsOrderSimple.setExpressCode(expressCode.getExpressCode());
            goodsOrderSimpleRepo.upSampleOrderExpress(goodsOrderSimple);
        }
    }

    /**
     * 删除寄样订单
     * @param sampleNo
     */
    /*public void delSampleOrder(String sampleNo ){
        GoodsOrderSimple goodsOrderSimple =goodsOrderSimpleRepo.findBySampleNo(sampleNo);
        if(goodsOrderSimple == null){
            throw new OcBizException(OcReturnCode.OC202012.getMsg(),OcReturnCode.OC202012.getCode());
        }
        goodsOrderSimpleRepo.delete(goodsOrderSimple.getGoodsOrderSimpleId().getId());
    }*/

}
