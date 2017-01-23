package com.pad.warehouse;

import com.pad.Data;
import com.pad.database.CDB;

/**
 * Created by Admin on 22.01.2017.
 */
public class DataUpdateService extends Thread {
    private CDB cdb = new CDB();
    private static final int UPDATE_PERIOD = 5000;//miliseconds

    public void run() {
        while (true){
            try {
                cdb.openConnectToCDB("localhost", 9042);
                Data.getInstance().updateData(cdb.getDataFromCDB());
                cdb.closeConnectToCDB();
                Thread.sleep(UPDATE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
