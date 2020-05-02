package com.jinshuo.mall.domain.user.model.merchantlogin;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.user.model.merchant.MerchantId;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户登录表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantManager extends IdentifiedEntity {

    /**
     * id
     */
    private MerchantManagerId merchantManagerId;

    /**
     * 信息表id
     */
    private MerchantId merchantId;

    /**
     * type
     */
    private Integer type;//类型  1高级  2普通

    /**
     * 登录用户名
     */
    private UserAccountId userAccountId;

    /**
     * name
     */
    private String name;

    /**
     * mobile
     */
    private String mobile;

    /**
     * idCard
     */
    private String idCard;

    /**
     * sex
     */
    private Integer sex;
}
