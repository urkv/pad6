package com.pad.client;

/**
 * Created by Admin on 28.01.2017.
 */

import com.pad.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

        public static void main(String args[]) {
            RestTemplate restTemplate = new RestTemplate();
            Scanner scanner = new Scanner(System.in);
            while(true){
                String request = scanner.nextLine();
                if(request.contains("GET")){
                    Employee employee = restTemplate.getForObject("http://localhost:8081/GET/employee?id=1", Employee.class);
                    System.out.println(employee.toString());
                }else if(request.contains("GET")&&request.contains("ALL")){

                }else if(request.contains("PUT")){

                }else if(request.contains("UPDATE")){

                }else if(request.contains("DELETE")){

                }

                ResponseEntity<Employee[]> response = restTemplate.getForEntity("http://localhost:8081/GET/employee/ALL", Employee[].class);

                List<Employee> employees = Arrays.asList(response.getBody());
                //restTemplate.getForEntity("http://localhost:8081/GET/employee/ALL",List.class, Employee.class);
                for (Employee employee1 : employees) {
                    System.out.println(employee1.toString());
                }
            }


        }

    }
