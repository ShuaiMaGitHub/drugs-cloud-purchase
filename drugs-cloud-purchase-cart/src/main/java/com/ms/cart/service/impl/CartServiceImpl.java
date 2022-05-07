package com.ms.cart.service.impl;

import com.ms.auth.entity.UserInfo;
import com.ms.cart.client.GoodsClient;
import com.ms.cart.interceptor.LoginInterceptor;
import com.ms.cart.pojo.Cart;
import com.ms.cart.service.CartService;
import com.ms.common.utils.JsonUtils;
import com.ms.item.pojo.Sku;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Shuai.Ma
 * @date 2022/4/12 20:35
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GoodsClient goodsClient;

    static final String KEY_PREFIX = "user:cart:";

    @Override
    public void addCart(Cart cart) {
        //获取用户登录
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        //查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        String key = cart.getSkuId().toString();

        Integer num = cart.getNum();
        //判断当前商品是否在购物车内
        if(hashOperations.hasKey(key)){
            //在就更新数量
            String cartIJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartIJson, Cart.class); //反序列化
            // 修改购物车数量
            cart.setNum(cart.getNum() + num);
        }else{
            //不在就新增购物车
            cart.setUserId(userInfo.getId());
            // 其它商品信息，需要查询商品服务
            Sku sku = this.goodsClient.querySkuBySkuId(cart.getSkuId());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
        }
        // 将购物车数据写入redis
        hashOperations.put(cart.getSkuId().toString(), JsonUtils.serialize(cart));

    }

    /**
     * 查询购物车列表
     *
     * @return
     */
    @Override
    public List<Cart> queryCartList() {
        // 获取登录用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();

        // 判断是否存在购物车
        String key = KEY_PREFIX + userInfo.getId();
        if(!this.redisTemplate.hasKey(key)){
            // 不存在，直接返回
            return null;
        }
        //获取用户购物车记录
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        List<Object> cartsJson = hashOps.values();
        // 判断是否有数据
        if(CollectionUtils.isEmpty(cartsJson)){
            return null;
        }
        // 查询购物车数据,将List<Object>集合转化为List<Cart>集合
        return cartsJson.stream().map(o -> JsonUtils.parse(o.toString(), Cart.class)).collect(Collectors.toList());
    }

    /**
     * 修改商品数量
     * @param cart
     * @return
     */
    @Override
    public void updateNum(Cart cart) {
        // 获取登陆信息
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        String key = KEY_PREFIX + userInfo.getId();
        // 获取hash操作对象
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(key);
        // 获取购物车信息
        String cartJson = hashOperations.get(cart.getSkuId().toString()).toString();
        Cart cart1 = JsonUtils.parse(cartJson, Cart.class);
        // 更新数量
        cart1.setNum(cart.getNum());
        // 写入购物车
        hashOperations.put(cart.getSkuId().toString(), JsonUtils.serialize(cart1));
    }

    /**
     * 购物车删除商品
     * @param skuId
     * @return
     */
    @Override
    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }
}
