package com.atguigu.cloud.service;




import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * @author aiqiku
 * @create 2024/4/9 21:03
 */
public interface PayService {

    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);

    Pay   getById(Integer id);
    List<Pay> getAll();
}
