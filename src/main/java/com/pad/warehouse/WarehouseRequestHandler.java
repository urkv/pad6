package com.pad.warehouse;

import com.pad.Data;
import com.pad.Employee;
import com.pad.database.CassandraDatabase;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 22.01.2017.
 */
@RestController
public class WarehouseRequestHandler {

    @RequestMapping(value = "/GET/employee",params = "id", method = RequestMethod.GET)
    public Employee getEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        return Data.getInstance()
                .getEmployees()
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .get();
    }

    @RequestMapping(value = "/GET/employee/ALL", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return Data.getInstance()
                .getEmployees()
                .stream()
                .sorted(Comparator.comparingInt(Employee::getId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "GET/employee", method = RequestMethod.GET)
    public List<Employee> getEmployeesWithOffsetAndLimit(@RequestParam(value = "offset") int offset,
                                                         @RequestParam(value = "limit") int limit
    ){
        return Data.getInstance()
                .getEmployees()
                .stream()
                .sorted(Comparator.comparingInt(Employee::getId))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/DELETE/employee", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        CassandraDatabase cdb = new CassandraDatabase();
        cdb.openConnectToCDB();
        cdb.deleteEmployee(id);
        cdb.closeConnectToCDB();
    }

    @RequestMapping(value = "/PUT/employee", method = RequestMethod.PUT)
    @ResponseBody
    public void insertEmployee(@RequestParam("id") int id,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary
                               ) {
        CassandraDatabase cdb = new CassandraDatabase();
        cdb.openConnectToCDB();
        cdb.insertEmployee(id, firstName, lastName, department, salary);
        cdb.closeConnectToCDB();
    }

    @RequestMapping(value = "/UPDATE/employee", method = RequestMethod.POST)
    @ResponseBody
    public Employee updateEmployee(@RequestParam("id") int id,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary
    ) {
        CassandraDatabase cdb = new CassandraDatabase();
        cdb.openConnectToCDB();
        cdb.updateEmployee(id, firstName, lastName, department, salary);
        Employee employee = cdb.getEmployeeById(id);
        cdb.closeConnectToCDB();
        return employee;
    }
}
