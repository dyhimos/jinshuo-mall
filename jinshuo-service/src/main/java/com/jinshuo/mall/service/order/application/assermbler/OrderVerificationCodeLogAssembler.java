package com.jinshuo.mall.service.order.application.assermbler;

import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeLog;
import com.jinshuo.mall.service.order.application.dto.OrderVerificationCodeLogDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by 19458 on 2019/11/22.
 */
@Component
public class OrderVerificationCodeLogAssembler {


    public static OrderVerificationCodeLogDto assembleCartDto(OrderVerificationCodeLog log) {
        if (log == null) {
            return null;
        }
        OrderVerificationCodeLogDto dto = new OrderVerificationCodeLogDto();
        BeanUtils.copyProperties(log, dto);
        dto.setId(log.getOrderVerificationCodeLogId().getId());
        return dto;
    }
}
