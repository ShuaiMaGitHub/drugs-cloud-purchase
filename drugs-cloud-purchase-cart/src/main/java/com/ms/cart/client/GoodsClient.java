package com.ms.cart.client;

import com.ms.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Shuai.Ma
 * @date 2022/4/12 21:07
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
