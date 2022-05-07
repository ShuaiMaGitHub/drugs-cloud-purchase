package com.ms.order.service.api;

import com.ms.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Shuai.Ma
 * @date 2022/4/16 22:35
 */
@FeignClient(value = "drugs-cloud-purchase-gateway", path = "/api/item")
public interface GoodService extends GoodsApi {
}
