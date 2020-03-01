package com.yimkong.facade;

import com.yimkong.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author yg
 * description
 * 远程调用,value为需要调用的服务的名字,例如调用service2模块的接口(未集合ribbon)
 * date 2020/3/1
 */
@FeignClient(value = "eureka-client2", configuration = {FeignConfiguration.class})
public interface UserRemoteClient {
    @GetMapping(value = "user/hello")
    String feignHello();
}
