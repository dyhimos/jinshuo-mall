package com.jinshuo.core.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jinshuo.core.exception.YmReturnCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname WrapperResponse
 * @Description 通用返回对象
 * @Date 2019/6/17 18:15
 * @Created by dyh
 */
@ApiModel(description= "返回响应数据")
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WrapperResponse<T> implements Serializable {
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    private int retCode;
    private String retMsg;
    private T result;


    public WrapperResponse() {}

    public boolean isSuccess() {
        return result != null;
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> WrapperResponse<T> success(T data) {
        WrapperResponse<T> resp = new WrapperResponse<>();
        resp.setRetCode(YmReturnCode.SYS000000.getCode());
        resp.setRetMsg(YmReturnCode.SYS000000.getMsg());
        resp.setResult(data);
        return resp;
    }
    /**
     * 成功返回结果
     *
     */
    public static <T> WrapperResponse<T> success() {
        return success(null);
    }


    /**
     * 失败返回结果
     * @param error 提示信息
     */
    public static <T> WrapperResponse<T> fail(String error) {
        WrapperResponse<T> resp = new WrapperResponse<>();
        resp.setRetCode(YmReturnCode.SYS000001.getCode());
        resp.setRetMsg(error);
        return resp;
    }

    /**
     *失败返回结果
     * @param errorCode 返回代码
     * @param error 提示信息
     * @param <T>
     * @return
     */
    public static <T> WrapperResponse<T> fail(int errorCode , String error) {
        WrapperResponse<T> resp = new WrapperResponse<>();
        resp.setRetCode(errorCode);
        resp.setRetMsg(error);
        return resp;
    }


    public T getResult() {
        return this.result;
    }





}
