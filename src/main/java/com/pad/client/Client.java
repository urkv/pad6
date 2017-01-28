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
//                         if(request.contains("id"))
//                         {
//                                 Employee employee = restTemplate.getForEntity(request, Employee.class);
//                                 System.out.println(employee.toString());
//                         }
//                         if(request.contains("offset") || request.contains("ALL"))
//                         {
//                                 ResponseEntity<Employee[]> response = restTemplate.getForEntity(request, Employee[].class);
//                                 List<Employee> employees = Arrays.asList(response.getBody());
//                                 for (Employee anEmployee : employees) {
//                                         System.out.println(anEmployee.toString());
//                                 }
//                         }
                        ResponseEntity<Employee[]> response = restTemplate.getForEntity(entityString, Employee[].class);
                        List<Employee> employees = Arrays.asList(response.getBody());
                        for (Employee anEmployee : employees) {
                            System.out.println(anEmployee.toString());
                        }
                }else if(request.contains("PUT")){
                    restTemplate.put(request);
                    System.out.println("WAS PUT!");
                }else if(request.contains("UPDATE")){
                    restTemplate.update(request);
                    System.out.println("UPDATED!");
                }else if(request.contains("DELETE")){
                    restTemplate.delete(request);
                    System.out.println("DELETED!");
                }else {
                    System.out.println("BAD REQUEST!");
                }
                 


                    
            }


        }

    }
