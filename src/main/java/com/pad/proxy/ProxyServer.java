package com.pad.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Admin on 28.01.2017.
 */
@ComponentScan
@EnableAutoConfiguration
public class ProxyServer {
    public static void main(String[] args) {
        SpringApplication.run(ProxyServer.class, args);
    }
}
