package com.pad.warehouse;

import com.pad.Data;
import com.pad.database.CassandraDatabase;

/**
 * Created by Admin on 22.01.2017.
 */
public class DataUpdateService extends Thread {
    private CassandraDatabase cdb = new CassandraDatabase();
    private static final int UPDATE_PERIOD = 5000;//miliseconds

    public void run() {
        while (true){
            try {
                cdb.openConnectToCDB();
                Data.getInstance().updateData(cdb.getAllEmployees());
                System.out.println("DATA UPDATED FROM CDB");
                cdb.closeConnectToCDB();
                Thread.sleep(UPDATE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
