package com.ms.client;

import com.ms.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 17:04
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
