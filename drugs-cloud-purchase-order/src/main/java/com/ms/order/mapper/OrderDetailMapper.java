package com.ms.order.mapper;

import com.ms.order.pojo.OrderDetail;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author Shuai.Ma
 * @date 2022/4/16 22:24
 */
public interface OrderDetailMapper extends Mapper<OrderDetail>, InsertListMapper<OrderDetail> {
}
