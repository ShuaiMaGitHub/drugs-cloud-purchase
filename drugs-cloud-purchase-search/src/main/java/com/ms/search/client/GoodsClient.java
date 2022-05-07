package com.ms.search.client;

import com.ms.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/17 15:49
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {







}
