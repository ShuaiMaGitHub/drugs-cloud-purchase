package com.ms.goods.client;

import com.ms.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/17 16:32
 */
@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi{

}
