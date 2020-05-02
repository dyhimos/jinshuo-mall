package com.jinshuo.mall.domain.user.model.address;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UserIdUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends IdentifiedEntity {
    private AddressId addressId;//编号
    private Long memId;//会员编号
    private String province;//所在省
    private String city;//所在市
    private String districts;//所在区县
    private String address;//详细地址
    private String receiver;//收货人
    private String phone;//联系手机
    private Integer isDefault;//是否默认收货地址 0是 1否

    public Address insert() {
        this.addressId = new AddressId(CommonSelfIdGenerator.generateId());
        this.memId = UserIdUtils.getUserId();
        super.preInsert();
        return this;
    }

    public Address update() {
        this.updateDate = new Date();
        return this;
    }
}
