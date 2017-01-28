package com.pad.warehouse;

import com.pad.Data;
import com.pad.Employee;
import com.pad.database.CassandraDatabase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Admin on 22.01.2017.
 */
@RestController
public class RequestHandler {
    //http://localhost:8081/GET/employee?id=1
    //http://localhost:8081/GET/employee/ALL
    //http://localhost:8081/DELETE/employee?id=5
    //http://localhost:8081/PUT/employee?id=5&first_name=yuf&last_name=vl&department=it&salary=10000
    //http://localhost:8081/UPDATE/employee?id=5&first_name=yuf&last_name=vl&department=it&salary=10000
    @RequestMapping("/GET/employee")
    public Employee getEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        return Data.getInstance()
                .getEmployees()
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .get();
    }

    @RequestMapping("/GET/employee/ALL")
    public List<Employee> getAll() {
        return Data.getInstance().getEmployees();
    }

    @RequestMapping("/DELETE/employee")
    public void deleteEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        CassandraDatabase cdb = new CassandraDatabase();
        cdb.openConnectToCDB();
        cdb.deleteEmployee(id);
        cdb.closeConnectToCDB();
    }

    @RequestMapping("/PUT/employee")
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

    @RequestMapping("/UPDATE/employee")
    public void updateEmployee(@RequestParam("id") int id,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary
    ) {
        CassandraDatabase cdb = new CassandraDatabase();
        cdb.openConnectToCDB();
        cdb.updateEmployee(id, firstName, lastName, department, salary);
        cdb.closeConnectToCDB();
    }
}
