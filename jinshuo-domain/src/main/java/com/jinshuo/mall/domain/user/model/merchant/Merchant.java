package com.jinshuo.mall.domain.user.model.merchant;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant extends IdentifiedEntity {
    private MerchantId merchantId;
    private UserAccountId userAccountId;
    private Integer type;
    private String name;
    private String linkMan;
    private String linkPhone;
}
