package com.ms.cart.service;

import com.ms.cart.pojo.Cart;

import java.util.List;

/**
 * @author Shuai.Ma
 * @date 2022/4/12 20:34
 */
public interface CartService {

    void addCart(Cart cart);

    List<Cart> queryCartList();

    void updateNum(Cart cart);

    void deleteCart(String skuId);
}
