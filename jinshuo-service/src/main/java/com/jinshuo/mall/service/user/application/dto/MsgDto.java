package com.jinshuo.mall.service.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/2/21.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgDto {
    private String username;
    private String password;
    private String signName;
    private String mobile;
    private String content;
    private Long shopId;
}
