package com.jinshuo.mall.domain.user.model.wxTemplateMessage;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Classname WxConfig
 * @Description 微信配置信息表
 * @Date 2019/6/16 :01
 * @Created by mgh
 * @author mgh
 */
@ToString(callSuper = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxTemplateMessage  extends IdentifiedEntity {

    /**
     * id
     */
    private WxTemplateMessageId wxTemplateMessageId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 微信消息模板，模板编号
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板类型 1:购买成功通知 2：实物发货通知通知 3：虚拟发码通知
     */
    private Integer templateType;

    /**
     * 模板内容
     */
    private String templateContent;

}
