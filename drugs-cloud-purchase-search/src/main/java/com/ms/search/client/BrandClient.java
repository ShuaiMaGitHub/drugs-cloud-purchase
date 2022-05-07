package com.ms.search.client;

import com.ms.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/17 16:30
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
