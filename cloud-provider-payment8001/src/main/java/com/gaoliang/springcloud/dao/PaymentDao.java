package com.gaoliang.springcloud.dao;


import com.gaoliang.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * Description: 支付模块Dao
 * @Author: gaoliang
 * @Date: 2020/10/25 0:23
 */
@Mapper
public interface PaymentDao {

    public int insert(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
