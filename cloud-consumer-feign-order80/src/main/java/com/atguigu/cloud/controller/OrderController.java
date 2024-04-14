package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.entities.enums.ReturnCodeEnum;
import com.atguigu.cloud.respon.ResultData;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author aiqiku
 * @create 2024/4/10 12:38
 */
@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData<String> resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("调用开始----: " + DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束----: " + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;

    }
    @PostMapping("/feign/pay/list")
    public ResultData listOrder(){
        ResultData<List> listResultData = payFeignApi.payList();
        return listResultData;
    }
    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb()
    {
        return payFeignApi.mylb();
    }
}