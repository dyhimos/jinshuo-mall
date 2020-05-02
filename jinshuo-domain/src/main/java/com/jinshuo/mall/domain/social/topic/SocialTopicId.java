package com.jinshuo.mall.domain.social.topic;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicId {

    @com.fasterxml.jackson.databind.annotation.JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}