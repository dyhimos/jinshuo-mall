package com.jinshuo.core.model;

import com.jinshuo.core.model.enums.Status;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Classname IdentifiedEntity
 * @Description TODO
 * @Date 2019/7/9 14:17
 * @Created by dyh
 */
@Data
@Getter
@Setter(AccessLevel.PUBLIC)
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public abstract class IdentifiedEntity extends BaseEntity {


    /** 备注**/
    public String remarks;
    /***创建时间**/
    public Date createDate;
    /**更新时间**/
    public Date updateDate;
    /**状态**/
    public Status status;
    /**版本**/
    public Integer version;


    public IdentifiedEntity() {
        super();
        this.status = Status.NORMAL;
    }

    /**
     * 更新之前需要手动调用
     */
    @Override
    public void preUpdate() {
        if (!super.isNewRecord) {
            this.updateDate = new Date();
        }
    }

    /**
     * 插入之前执行 需要手动调用
     */
    @Override
    public void preInsert() {
        if (StringUtils.isBlank(getRemarks())) {
            setRemarks("--");
        }
        this.createDate = new Date();
        this.updateDate = new Date();
    }
}


