package com.xjw.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    public WebConfig(){
        System.out.println("调用构造函数WebConfig");
    }
}
