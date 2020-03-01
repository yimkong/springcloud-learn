package com.yimkong;

import com.yimkong.facade.UserRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author yg
 * description
 * date 2020/2/20
 */
@RestController
public class UserController {
    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("user/hello")
    public String hello() {
        return "hello1";
    }

    @GetMapping("testFeign")
    public String testFeign() {
        String s = userRemoteClient.feignHello();
        return s;
    }
}
