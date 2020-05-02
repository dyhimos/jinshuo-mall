package com.jinshuo.core.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname BaseRequest
 * @Description TODO
 * @Date 2019/6/23 22:45
 * @Created by dyh
 */
@Data
@Accessors(chain = true)
public abstract class BaseRequest implements Request {

    private String source;
    private Operator operator;
}
