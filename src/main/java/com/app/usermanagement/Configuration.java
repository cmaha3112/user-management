package com.app.usermanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Bean
    public String sample(){
        return "hello world";
    }

}
