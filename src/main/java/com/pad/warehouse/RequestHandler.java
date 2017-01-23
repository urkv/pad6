package com.pad.warehouse;

import com.pad.Data;
import com.pad.Employee;
import com.pad.database.CDB;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.01.2017.
 */
@RestController
public class RequestHandler {

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
    public List<Employee > getAll() {
        return Data.getInstance().getEmployees();
    }

    @RequestMapping("/DELETE/employee")
    public void deleteEmployeeById(@RequestParam(value="id", required=false, defaultValue="0") int id) {
        CDB cdb = new CDB();
        cdb.openConnectToCDB();
        cdb.deleteDataInCDB(id);
        cdb.closeConnectToCDB();
    }

    @RequestMapping("/PUT/employee")
    public void insertEmployee(@RequestParam("id") int id,
                               @RequestParam("firstname") String firstName,
                               @RequestParam("lastname") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") Double salary
                               ) {
        //System.out.println(id+firstName+lastName+department+salary);
        //http://localhost:8081/PUT/employee?id=5&firstname=yuf&lastname=vl&department=it&salary=10000
        CDB cdb = new CDB();
        cdb.openConnectToCDB();
        cdb.insertRecordToDB(id,firstName, lastName, department,salary);
        cdb.closeConnectToCDB();
    }
}
