package com.jinshuo.mall.domain.item.lattice;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by dongyh on 2019/9/24.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lattice extends IdentifiedEntity {
    private LatticeId latticeId;

    @NotNull(message = "父分类不能为空")
    private String name;

    @NotNull(message = "排序序列")
    private Integer orderSeq;

    @NotNull(message = "类目图片")
    private String pictureUrl;

    private Long shopId;

    @NotNull(message = "是否展示 0:是 1:是展示")
    private Integer isShow;

    public Lattice insert(){
        this.preInsert();
        this.latticeId = new LatticeId(CommonSelfIdGenerator.generateId());
        return this;
    }

    public Lattice update(Long id){
        this.latticeId = new LatticeId(id);
        this.updateDate = new Date();
        return this;
    }
}
