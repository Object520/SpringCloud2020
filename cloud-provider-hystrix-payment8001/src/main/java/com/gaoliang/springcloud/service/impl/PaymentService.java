package com.gaoliang.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfoOK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoOK, id: " + id;
    }

    /**
     * 超时访问，演示降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfoTimeout(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int age = 10/0;//不管是超时还是程序报错都会走备用方法
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeout, id: " + id + ", 耗时: ";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        // 友好提示系统繁忙等
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeoutHandler, id: " + id;
    }

}
