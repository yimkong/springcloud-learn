package com.yimkong.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author yg
 * description
 * 远程调用,value为需要调用的服务的名字,例如调用service2模块的接口
 * date 2020/3/1
 */
@FeignClient(value = "eureka-client2")
public interface UserRemoteClient {
    @GetMapping(value = "user/hello")
    String feignHello();
}
