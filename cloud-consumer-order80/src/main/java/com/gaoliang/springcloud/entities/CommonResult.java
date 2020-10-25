package com.gaoliang.springcloud.entities;

import lombok.Data;

/*
 * Description:Json封装体
 * @Author: gaoliang
 * @Date: 2020/10/25 0:07
 */
@Data
public class CommonResult<T>
{
    private Integer code;

    private String message;

    private T  data;

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult() {
    }
}
