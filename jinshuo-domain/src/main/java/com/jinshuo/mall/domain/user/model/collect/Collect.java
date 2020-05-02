package com.jinshuo.mall.domain.user.model.collect;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 19458 on 2019/7/19.
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collect extends IdentifiedEntity {
    /**
     * ID
     */
    private CollectId collectId;
    /**
     * 会员ID
     */
    private MemberId memId;
    /**
     * 收藏产品类型
     */
    private Integer type;
    /**
     * 收藏产品
     */
    private Long targetId;

    public Collect insert() {
        checkRepeat();
        this.collectId = new CollectId(CommonSelfIdGenerator.generateId());
        this.memId = new MemberId(UserIdUtils.getUserId());
        super.preInsert();
        return this;
    }

    /**
     * 判断是否重复添加
     */
    public Collect checkRepeat() {
        /*List<Collect> collects = SpringUtil.getBean(CollectRepo.class).queryByTargetId(UserIdUtils.getUserId(), this.getTargetId());
        if (null != collects && collects.size() > 0) {
            log.info("重复添加了！！！！");
            throw new UcBizException(UcReturnCode.UC200001.getMsg());
        }*/
        return this;
    }
}
