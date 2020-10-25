package com.gaoliang.springcloud.service.impl;

import com.gaoliang.springcloud.dao.PaymentDao;
import com.gaoliang.springcloud.entities.Payment;
import com.gaoliang.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
