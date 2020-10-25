package com.gaoliang.springcloud.service;

import com.gaoliang.springcloud.entities.Payment;

/*
 * Description: 支付Service
 * @Author: gaoliang
 * @Date: 2020/10/25 0:47
 */
public interface PaymentService {

    public int insert(Payment payment);

    public Payment getPaymentById(Long id);
}
