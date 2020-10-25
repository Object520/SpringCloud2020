package com.gaoliang.springcloud.controller;

import com.gaoliang.springcloud.entities.CommonResult;
import com.gaoliang.springcloud.entities.Payment;
import com.gaoliang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * Description: 支付Controller
 * @Author: gaoliang
 * @Date: 2020/10/25 0:52
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        if (result > 0) {
             return new CommonResult(200, "插入数据库成功, serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {//前端请求 http://localhost:8001/payment/get/1
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            log.info("查询成功, id{}", id);
             return new CommonResult(200, "查询成功, serverPort:" + serverPort, payment);
        } else {
            log.error("查询失败, id{}", id);
            return new CommonResult(444, "查询失败", null);
        }
    }


    @GetMapping(value="/payment/discovery")
    public Object discovery() {
        // 获取服务清单列表
        List<String> services = discoveryClient.getServices();
        for(String element: services) {
            log.info("############element:" + element);
        }
        // 获取实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances) {
            // 服务名 主机名 端口 uri
            log.info("##########" + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value="/payment/timeout")
    public String timeout() {
        try {
            // 睡眠3秒,模拟超时
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
