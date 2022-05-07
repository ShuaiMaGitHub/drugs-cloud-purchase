package com.ms.order.mapper;

import com.ms.order.pojo.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Shuai.Ma
 * @date 2022/4/16 22:25
 */
public interface OrderMapper extends Mapper<Order> {

    List<Order> queryOrderList(
            @Param("userId") Long userId,
            @Param("status") Integer status);
}