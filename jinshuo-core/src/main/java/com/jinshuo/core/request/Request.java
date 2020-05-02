package com.jinshuo.core.request;

import java.io.Serializable;

/**
 * @Classname Request
 * @Description TODO
 * @Date 2019/6/17 18:00
 * @Created by dyh
 */
public interface Request extends Serializable {

    /**
     * 请求来源,系统名称或前端终端。
     */
    String getSource();

    /**
     * 操作人
     */
    Operator getOperator();
}