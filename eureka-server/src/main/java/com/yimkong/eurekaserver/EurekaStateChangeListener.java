package com.yimkong.eurekaserver;

import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * author yg
 * description 事件机制监控服务
 * date 2020/2/23
 */
@Component
public class EurekaStateChangeListener {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.err.println(event.getAppName() + "\t" + event.getServerId() + " 服务下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        System.err.println(eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName() + " 服务注册");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.err.println(event.getAppName() + "\t" + event.getServerId() + " 服务续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println("注册中心启动");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.err.println("eureka server启动");
    }
}
