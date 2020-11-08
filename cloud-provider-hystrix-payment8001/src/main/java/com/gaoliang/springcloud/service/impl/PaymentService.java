package com.gaoliang.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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


    /**
     *  服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name="circuitBreaker.enabled", value="true"),
            // 请求次数
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
            // 时间窗口期
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60")
            // 以上参数表示10秒内失败超过6次跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0) {
            throw new RuntimeException("*****id不能为负");
        }
        // hutool,国产工具类包,https://www.hutool.cn/docs/#/
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功, 流水号: " + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "paymentCircuitBreakerFallback: id不能负数,请稍后再试, id: " + id;
    }


}
