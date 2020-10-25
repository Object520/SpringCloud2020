package com.gaoliang.springcloud.controller;

import com.gaoliang.springcloud.entities.CommonResult;
import com.gaoliang.springcloud.entities.Payment;
import com.gaoliang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        if (result > 0) {
             return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id) {//前端请求 http://localhost:8001/payment/get/1
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            log.info("查询成功, id{}", id);
             return new CommonResult(200, "查询成功", payment);
        } else {
            log.error("查询失败, id{}", id);
            return new CommonResult(444, "查询失败", null);
        }
    }

}
