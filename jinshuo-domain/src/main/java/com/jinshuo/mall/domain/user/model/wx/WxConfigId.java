package com.jinshuo.mall.domain.user.model.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname WxConfigId
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WxConfigId {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}
