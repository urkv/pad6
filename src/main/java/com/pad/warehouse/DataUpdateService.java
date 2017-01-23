package com.pad.warehouse;

import com.pad.Data;
import com.pad.Employee;
import com.pad.database.CDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.01.2017.
 */
public class DataUpdateService extends Thread {
    private CDB cdb = new CDB();
    private static final int UPDATE_PERIOD = 5000;//miliseconds

    //public DataUpdateService(List<Employee> employees) {
    //    this.employees = employees;
    //}

    public void run() {
        while (true){
            try {
                //cdb.openConnectToCDB("localhost", 9042);
                //Data.getInstance().updateData(cdb.getDataFromCDB());
                //cdb.closeConnectToCDB();
                Data.getInstance().updateData(getData());
                Thread.sleep(UPDATE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Employee> getData(){
        List<Employee> employees = new ArrayList<Employee>(){{
            add(new Employee(1,"Velstadt","The Royal Aegis","Undead Crypt", 50000.0));
            add(new Employee(2,"Vendrick","The King","Undead Crypt", 90000.0));
            add(new Employee(3,"Aldia","Scholar of the First Sin","Throne of Want", 0.0));
        }};
        return employees;
    }
}
