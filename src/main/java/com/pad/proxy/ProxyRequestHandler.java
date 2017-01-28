package com.pad.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pad.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Admin on 28.01.2017.
 */
@RestController
public class ProxyRequestHandler {
    JedisCache myJed;

    @RequestMapping(value = "/GET/employee", params = "id", method = RequestMethod.GET)
    public Employee getEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        myJed = JedisCache.getInstance();

        if (!myJed.jedis.smembers(id + "id").isEmpty()) {
            System.out.println("GET FROM JEDIS");
            return getEmployee(myJed.jedis.smembers(id + "id").toString());
        }
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject("http://localhost:808"+or12()+"/GET/employee?id=" + id, Employee.class);
        myJed.jedisAdd( id + "id", getJASONasString(employee));
        return employee;
    }


    @RequestMapping(value = "/GET/employee/ALL", method = RequestMethod.GET)
    public List<Employee> getAll() {
        myJed = JedisCache.getInstance();
        if (!myJed.jedis.smembers("ALL").isEmpty()) {
            System.out.println("GET FROM JEDIS");
            return getEmployees(myJed.jedis.smembers("ALL").toString());
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> response = restTemplate.getForEntity("http://localhost:808"+or12()+"/GET/employee/ALL", Employee[].class);
        List<Employee> employees = Arrays.asList(response.getBody());
        myJed.jedisAdd( "ALL", getJASONasString(employees));
        return employees;
    }

    @RequestMapping(value = "GET/employee", method = RequestMethod.GET)
    public List<Employee> getEmployeesWithOffsetAndLimit(@RequestParam(value = "offset") int offset,
                                                         @RequestParam(value = "limit") int limit
    ){
        myJed = JedisCache.getInstance();
        if (!myJed.jedis.smembers("offset=" + offset + "limit=" + limit).isEmpty()) {
            System.out.println("GET FROM JEDIS");
            return getEmployees(myJed.jedis.smembers("offset=" + offset + "limit=" + limit).toString());
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:808"+or12()+"/GET/employee?" +
                "offset=" + offset +
                "&limit=" + limit;
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> employees = Arrays.asList(response.getBody());
        myJed.jedisAdd( "offset=" + offset + "limit=" + limit, getJASONasString(employees));
        return employees;
    }

    @RequestMapping(value = "/DELETE/employee", method = RequestMethod.DELETE)
    public void deleteEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(new URI("http://localhost:808"+or12()+"/DELETE/employee?id=" + id), null);
    }

    @RequestMapping(value = "/PUT/employee")
    public void insertEmployee(@RequestParam("id") int id,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary) throws MalformedURLException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:808"+or12()+"/INSERT/employee?" +
                "id=" + id +
                "&first_name=" + firstName +
                "&last_name=" + lastName +
                "&department=" + department +
                "&salary" + salary, null);
    }

    @RequestMapping(value = "/UPDATE/employee", method = RequestMethod.POST)
    public void updateEmployee(@RequestParam("id") int id,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary)  {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:808"+or12()+"/INSERT/employee?" +
                "id=" + id +
                "&first_name=" + firstName +
                "&last_name=" + lastName +
                "&department=" + department +
                "&salary" + salary, null, Employee.class);
    }

    private String getJASONasString(List<Employee> employees) {
        String serialized = new String();
        try {
            serialized = new ObjectMapper().writeValueAsString(employees);
            System.out.println(serialized);
        } catch (JsonProcessingException ie) {
            ie.printStackTrace();
        }
        return serialized;
    }

    private String getJASONasString(Employee employee) {
        String serialized = new String();
        try {
            serialized = new ObjectMapper().writeValueAsString(employee);
            System.out.println(serialized);
        } catch (JsonProcessingException ie) {
            ie.printStackTrace();
        }
        return serialized;
    }

    private List<Employee> getEmployees(String s){
        ObjectMapper jacksonMapper = new ObjectMapper();
        List<Employee> deserialized = new ArrayList<>();
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" + s);
        try {
            deserialized = jacksonMapper.readValue(s, new TypeReference<List<Employee>>() {});
        }catch (JsonProcessingException ie) {
            ie.printStackTrace();
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        return deserialized;
    }

    private Employee getEmployee(String s){
        ObjectMapper jacksonMapper = new ObjectMapper();
        Employee deserialized = new Employee();
        try {
            deserialized = jacksonMapper.readValue(s, Employee.class);
        }catch (JsonProcessingException ie) {
            ie.printStackTrace();
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        return deserialized;
    }

    private int or12(){
        Random random = new Random();
        return random.nextInt(2-1) + 1;//int Result = r.nextInt(High-Low) + Low;
    }
}



//http://localhost:8080/GET/employee?id=1
//http://localhost:8080/GET/employee/ALL
//http://localhost:8080/GET/employee?offset=3&limit=6
//http://localhost:8080/DELETE/employee?id=6
//http://localhost:8080/PUT/employee?id=5&first_name=yuf&last_name=vl&department=it&salary=10000
//http://localhost:8080/UPDATE/employee?id=5&first_name=yuf&last_name=vl&department=it&salary=10000