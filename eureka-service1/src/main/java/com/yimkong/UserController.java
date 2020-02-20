package com.yimkong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author yg
 * description
 * date 2020/2/20
 */
@RestController
public class UserController {

    @GetMapping("user/hello")
    public String hello() {
        return "hello";
    }
}
