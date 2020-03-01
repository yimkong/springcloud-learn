package com.yimkong;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author yg
 * description
 * date 2020/3/1
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
