package com.grang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ipconfig {

    @Value(value = "${ip}")
    public static String ip;

    @Bean
    String returnIp() {
        return ip;
    }
}
