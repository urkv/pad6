package com.pad.database;

/**
 * Created by Admin on 19.01.2017.
 */
public class InitCassandraDatabase {
        public static void main(String[] args) {

            CassandraDatabase cdb = new CassandraDatabase();
            cdb.openConnectToCDB("localhost", 9042);

            cdb.prepareCDB();
            //вставляем все данные в таблицу
            cdb.fillEmployees();
            //вывод данных из таблицы
            cdb.showDataFromEmployees();

            //удаление записи
            cdb.deleteEmployee(10);
            //вставка запису
            cdb.insertEmployee(10, "adf", "asdf", "asdfasdf", 1000);
            //изменение записи
            cdb.updateEmployee(10, "Fukiko", "Ogisu", "Operations", 500000);

            cdb.showDataFromEmployees();
            //закрываем соединение
            cdb.closeConnectToCDB();

        }

    }
