package com.pad.database;

/**
 * Created by Admin on 19.01.2017.
 */
public class InitCassandraDatabase {
        public static void main(String[] args) {

            CDB cdb = new CDB();
            cdb.openConnectToCDB("localhost", 9042);

            cdb.prepareCDB();
            //вставляем все данные в таблицу
            cdb.insertDataToCDB();
            //вывод данных из таблицы
            cdb.getDataFromCDB();

            //удаление записи
            cdb.deleteDataInCDB(10);
            //вставка запису
            cdb.insertRecordToDB(10, "adf", "asdf", "asdfasdf", 1000);
            //изменение записи
            cdb.updateDataInCDB(10, "Fukiko", "Ogisu", "Operations", 500000);

            cdb.getDataFromCDB();
            //закрываем соединение
            cdb.closeConnectToCDB();

        }

    }
