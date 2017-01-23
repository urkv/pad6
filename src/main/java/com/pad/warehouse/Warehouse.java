package com.pad.warehouse;

import com.pad.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.01.2017.
 */
@ComponentScan
@EnableAutoConfiguration
public class Warehouse {

    public static void main(String[] args) throws InterruptedException {
        //int port = Integer.parseInt(args[0]);
        //InetSocketAddress warehouseAddress = new InetSocketAddress("localhost", port);
        SpringApplication.run(Warehouse.class, args);

        //List<Employee> employees = new ArrayList<Employee>();
        DataUpdateService dus = new DataUpdateService();
        dus.start();
        //RequestListener rl = new RequestListener();
        //rl.start();
        //while (true){
        //    System.out.println(">>");
        //    Thread.sleep(1000);
        //    employees = dus.getEmployees();
        //    for (Employee employee : employees) {
        //        System.out.println(employee.toString());
        //    }
        //}
    }

}
