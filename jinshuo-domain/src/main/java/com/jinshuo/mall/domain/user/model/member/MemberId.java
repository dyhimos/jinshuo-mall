package com.jinshuo.mall.domain.user.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname MemberId
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MemberId {
    private Long id;
}
