package com.gaoliang.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Description: 支付实体类
 * @Author: gaoliang
 * @Date: 2020/10/25 0:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Long id;

    private String serial;
}
