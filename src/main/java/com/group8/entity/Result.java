package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 业务状态码：1为成功，其他为失败
     */
    private Integer code;
    
    /**
     * 提示信息
     */
    private String message;
    
    /**
     * 返回数据
     */
    private T data;
    
    /**
     * 成功响应
     */
    public static <T> Result<T> success() {
        return new Result<>(1, "操作成功", null);
    }
    
    /**
     * 成功响应，带数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "操作成功", data);
    }
    
    /**
     * 成功响应，自定义消息
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(1, message, data);
    }
    
    /**
     * 失败响应
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }
    
    /**
     * 失败响应，默认错误码
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(0, message, null);
    }
}