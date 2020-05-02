package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 按钮
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ButtonDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String btn;
    private String name;
    private boolean check;
}
