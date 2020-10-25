package com.gaoliang.springcloud.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfoOK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoOK, id: " + id;
    }

    public String paymentInfoTimeout(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeout, id: " + id + ", 耗时: " + timeNumber;
    }
}
