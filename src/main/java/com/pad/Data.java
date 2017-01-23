package com.pad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.01.2017.
 */
public class Data {
    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
    }
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void updateData(List<Employee> employees){
        this.employees = employees;
    }
}
