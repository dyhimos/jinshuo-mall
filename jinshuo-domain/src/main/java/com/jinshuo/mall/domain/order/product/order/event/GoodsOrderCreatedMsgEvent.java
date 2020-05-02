package com.jinshuo.mall.domain.order.product.order.event;

import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 订单创建触发短信事件
 *
 * @Classname GoodsOrderCreatedMsgEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class GoodsOrderCreatedMsgEvent extends BaseDomainEvent {
    //发码产品短信内容
    public static String sendCodeContentMsg = "尊敬的{name}，您购买的{productName},{num}份的核销二维码链接为{url} 。预约电话18802095992，现场点击链接核销二维码，使用日期{date}，过期作废";
    /**
     * 接受者姓名
     */
    public String name;
    /**
     * 手机号码
     */
    public String mobile;
    /**
     * 短信内容
     */
    public String content;
}
