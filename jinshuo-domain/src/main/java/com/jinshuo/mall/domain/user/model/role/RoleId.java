package com.jinshuo.mall.domain.user.model.role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleId {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}
