package com.yimkong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * author yg
 * description
 * date 2020/2/20
 */
@RestController
public class ArticleController {
    @Autowired
    public RestTemplate restTemplate;

    /**
     * 直接调用服务提供方，没有用到注册中心
     *
     * @return
     */
    @GetMapping("/article/toHello")
    public String hello() {
        return restTemplate.getForObject("http://localhost:8081/user/hello", String.class);
    }

    /**
     * 直接通过注册中心调用 使用注册名spring.application.name作为路径
     * @return
     */
    @GetMapping("/article/toCenterHello")
    public String helloCenter() {
        return restTemplate.getForObject("http://eureka-client1/user/hello", String.class);
    }

}
