package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.respon.ResultData;

import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author aiqiku
 * @create 2024/4/9 21:12
 */
@RestController
@RequestMapping("/pay")
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/add")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println("pay = " + pay);
        int i = payService.add(pay);
        return ResultData.success("成功插入,返回值：" + i);
    }

    @PostMapping("/delete/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){

        return ResultData.success(payService.delete(id));
    }

    @PostMapping("/update")
    public ResultData<String> deletePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDTO,pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改,返回值：" + i);
    }

    @GetMapping("/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if (id <= 0){
            throw new RuntimeException("id不能为负数");
        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @PostMapping("/list")
    private ResultData<List<Pay>> payList(){
        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;
    @GetMapping("/get/info")
    public String getInFoByConsul(@Value("${atguigu.info}") String atguiguInfo ){
        return "atguiguInfo : " +atguiguInfo + "\t" + "port: " + port;
    }
}
