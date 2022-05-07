package com.ms.order.service;

import com.ms.common.pojo.PageResult;
import com.ms.order.pojo.Order;

/**
 * @author Shuai.Ma
 * @date 2022/4/16 22:23
 */
public interface OrderService{
    Long createOrder(Order order);

    Order queryById(Long id);

    PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status);

    Boolean updateStatus(Long id, Integer status);
}
