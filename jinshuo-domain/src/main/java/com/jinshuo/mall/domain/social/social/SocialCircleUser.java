package com.jinshuo.mall.domain.social.social;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialCircleUser {
    private SocialCircleUserId socialCircleUserId;

    private Long circleId;

    private Long userId;

    private Date time;

}