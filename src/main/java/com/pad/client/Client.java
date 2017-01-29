package com.pad.client;

/**
 * Created by Admin on 28.01.2017.
 */

import com.pad.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String url = scanner.nextLine();
                if (url.contains("GET")) {
                    if (url.contains("id")) {
                        Employee employee = restTemplate.getForObject(url, Employee.class);
                        System.out.println(employee.toString());
                    }else if (url.contains("offset") || url.contains("ALL")) {
                        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
                        List<Employee> employees = Arrays.asList(response.getBody());
                        employees.forEach(System.out::println);
                    }
                } else if (url.contains("PUT")) {
                    restTemplate.put(new URI(url), null);
                    System.out.println("WAS PUT!");
                } else if (url.contains("UPDATE")) {
                    restTemplate.postForObject(url, null, Employee.class);
                    System.out.println("UPDATED!");
                } else if (url.contains("DELETE")) {
                    restTemplate.delete(url);
                    System.out.println("DELETED!");
                } else {
                    System.out.println("BAD REQUEST!");
                }
            } catch (RestClientException e) {
                System.out.println("BAD REQUEST!");
                e.printStackTrace();
            } catch (URISyntaxException e) {
                System.out.println("BAD REQUEST!");
                e.printStackTrace();
            }
        }
    }
}