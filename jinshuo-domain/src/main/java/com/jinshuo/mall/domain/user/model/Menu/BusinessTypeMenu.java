package com.jinshuo.mall.domain.user.model.Menu;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/10/12.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTypeMenu extends IdentifiedEntity {
    private BusinessTypeMenuId businessTypeMenuId;
    private Integer businessTypeId;
    private Long menuId;

    public BusinessTypeMenu build(Integer businessTypeId, Long menuId) {
        this.businessTypeMenuId = new BusinessTypeMenuId(CommonSelfIdGenerator.generateId());
        this.businessTypeId = businessTypeId;
        this.menuId = menuId;
        return this;
    }
}
