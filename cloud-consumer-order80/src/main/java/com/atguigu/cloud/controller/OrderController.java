package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.respon.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author aiqiku
 * @create 2024/4/10 12:38
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {
    //引入consul微服务名字
    public static final String PaymentSrv_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/pay/add")
    public ResultData addOrder( PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @PostMapping("/pay/delete/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/delete/" + id,null, ResultData.class);
    }
    @PostMapping("/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/update", payDTO, ResultData.class);
    }
    @GetMapping("/pay/get/{id}")
    public ResultData updateOrder(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class,id);
    }
    @PostMapping("/pay/list")
    public ResultData listOrder(){
        return restTemplate.postForObject(PaymentSrv_URL +"/pay/list" , null, ResultData.class);
    }
    @GetMapping("/pay/get/info")
    public String getInFoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info",String.class);
    }
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
