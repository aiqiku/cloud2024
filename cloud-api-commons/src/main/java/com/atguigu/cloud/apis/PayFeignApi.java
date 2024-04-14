package com.atguigu.cloud.apis;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.respon.ResultData;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author aiqiku
 * @create 2024/4/12 18:50
 */
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    @PostMapping("/pay/add")
    ResultData<String> addPay(@RequestBody PayDTO payDTO);

    @PostMapping("/pay/delete/{id}")
     ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    @GetMapping("/pay/get/{id}")
     ResultData getById(@PathVariable("id") Integer id);

    @PostMapping("/pay/list")
     ResultData<List> payList();
    @GetMapping("/pay/get/info")
    String mylb();
}
