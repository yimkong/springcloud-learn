package com.yimkong;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
     * 因为不是使用别名访问，因此不能使用自动组装的restTemplate(使用了@LoadBalanced注解)
     *
     * @return
     */
    @GetMapping("/article/toHello")
    public String hello() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/user/hello", String.class);
    }

    /**
     * 直接通过注册中心调用 使用注册名spring.application.name作为路径
     *
     * @return
     */
    @GetMapping("/article/toCenterHello")
    public String helloCenter() {
        return restTemplate.getForObject("http://eureka-client1/user/hello", String.class);
    }

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 不通过restapi 而是通过内建的api获取别的服务的信息
     *
     * @return
     */
    @GetMapping("/article/infos")
    public Object getService1Info() {
        return eurekaClient.getInstancesByVipAddress("eureka-client1", false);
    }

    @GetMapping("/article/infoslist")
    public Object getService1Infos() {
        return discoveryClient.getInstances("eureka-client1");
    }

}
