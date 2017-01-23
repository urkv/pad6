package com.pad.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Admin on 22.01.2017.
 */
@ComponentScan
@EnableAutoConfiguration
public class Warehouse {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Warehouse.class, args);
        new DataUpdateService().start();
    }

}
