package com.pad.database;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pad.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleks Dark on 19.01.2017.
 */
public class CDB {
    private Cluster cluster;
    private Session session;

    public void openConnectToCDB(final String address, final int port){
        //открываем соединение с кластером
        this.cluster = Cluster.builder().addContactPoint(address).withPort(port).build();
        this.session = cluster.connect();
        System.out.println("Открываем соединение с кластером");
    }

    public void openConnectToCDB(){
        //открываем соединение с кластером
        this.cluster = Cluster.builder().addContactPoint("localhost").withPort(9042).build();
        this.session = cluster.connect();
        System.out.println("Открываем соединение с кластером");
    }

    //подготовка БД
    void prepareCDB(){
        String create_keyspace_query = "CREATE KEYSPACE IF NOT EXISTS staff WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':1};";
        String create_table_query = "CREATE TABLE IF NOT EXISTS employees(emp_id int PRIMARY KEY, "
                + "firstname text, "
                + "lastname text, "
                + "department text, "
                + "salary double);";

        //создание пространства ключей
        session.execute(create_keyspace_query);

        System.out.println("Пространство ключей создано");

        //используем пространство ключей
        session.execute("USE staff");

        //создание таблицы сотрудников
        session.execute(create_table_query);

        System.out.println("Таблица создана");
    }

    //добавление записей в таблицу
    public void insertDataToCDB(){
        String insert_into = "INSERT INTO staff.employees (emp_id, firstname, lastname, department, salary) ";
        String insert_values1 = "VALUES(1, 'Serj', 'Litovchenko', 'Administration', 100000) IF NOT EXISTS;";
        String insert_values2 = "VALUES(2, 'Andrew', 'Dixon', 'Product Development', 70000) IF NOT EXISTS;";
        String insert_values3 = "VALUES(3, 'Anthony', 'Chor', 'IS', 40000) IF NOT EXISTS;";
        String insert_values4 = "VALUES(4, 'Asley', 'Larsen', 'Operations', 50000) IF NOT EXISTS;";
        String insert_values5 = "VALUES(5, 'Beth', 'Silverberg', 'Customer Service', 45000) IF NOT EXISTS;";
        String insert_values6 = "VALUES(6, 'Clair', 'Hector', 'Product Development', 50000) IF NOT EXISTS;";
        String insert_values7 = "VALUES(7, 'David', 'Jaffe', 'Product Development', 60000) IF NOT EXISTS;";
        String insert_values8 = "VALUES(8, 'Denise', 'Smith', 'Product Development', 55000) IF NOT EXISTS;";
        String insert_values9 = "VALUES(9, 'Eric', 'Lang', 'Product Development', 65000) IF NOT EXISTS;";
        String insert_values10 = "VALUES(10, 'Fukiko', 'Ogisu', 'Operations', 45000) IF NOT EXISTS;";
        String insert_values11 = "VALUES(11, 'Helmut', 'Horrig', 'Product Development', 35000) IF NOT EXISTS;";
        String insert_values12 = "VALUES(12, 'Jae', 'Pak', 'Operations', 45000) IF NOT EXISTS;";
        String insert_values13 = "VALUES(13, 'Jeffrey', 'Pira', 'Human Resources', 40000) IF NOT EXISTS;";
        String insert_values14 = "VALUES(14, 'John', 'Tippet', 'Customer Services', 50000) IF NOT EXISTS;";
        String insert_values15 = "VALUES(15, 'Jolie', 'Lenehen', 'Finance', 55000) IF NOT EXISTS;";
        String insert_values16 = "VALUES(16, 'Jon', 'Grande', 'Product Development', 33000) IF NOT EXISTS;";
        String insert_values17 = "VALUES(17, 'Jonathan', 'Yound', 'Product Development', 37000) IF NOT EXISTS;";
        String insert_values18 = "VALUES(18, 'Kim', 'Abercrombe', 'Human Resources', 45000) IF NOT EXISTS;";
        String insert_values19 = "VALUES(19, 'John', 'Colon', 'Customer Service', 48000) IF NOT EXISTS;";
        String insert_values20 = "VALUES(20, 'Deborah', 'Lee', 'Product Development', 63000) IF NOT EXISTS;";

        String insert_query[] = {
                insert_into + insert_values1, insert_into + insert_values2,
                insert_into + insert_values3, insert_into + insert_values4,
                insert_into + insert_values5, insert_into + insert_values6,
                insert_into + insert_values7, insert_into + insert_values8,
                insert_into + insert_values9, insert_into + insert_values10,
                insert_into + insert_values11, insert_into + insert_values12,
                insert_into + insert_values13, insert_into + insert_values14,
                insert_into + insert_values15, insert_into + insert_values16,
                insert_into + insert_values17, insert_into + insert_values18,
                insert_into + insert_values19, insert_into + insert_values20,};
        //добавляем записи
        for (int i = 0; i < 20; i++){
            session.execute(insert_query[i]);
        }
    }

    //вывод записей из таблицы
    public void showDataFromCDB(){
        String select_query = "SELECT * FROM staff.employees";
        //получаем записи таблицы
        ResultSet query_result = session.execute(select_query);
        System.out.println("Table employees:");
        for (Row row : query_result) {
            System.out.format("%d %s %s %s %f\n", row.getInt("emp_id"), row.getString("firstname"),
                    row.getString("lastname"),  row.getString("department"), row.getDouble("salary"));
        }
    }
    //возвращает список сотрудников из таблици
    public List<Employee> getDataFromCDB(){
        List<Employee> employees = new ArrayList<Employee>();
        String select_query = "SELECT * FROM staff.employees";
        //получаем записи таблицы
        ResultSet query_result = session.execute(select_query);
        System.out.println("Table employees:");
        for (Row row : query_result) {
            employees.add(
                    new Employee(
                            row.getInt("emp_id"),
                            row.getString("firstname"),
                            row.getString("lastname"),
                            row.getString("department"),
                            row.getDouble("salary")));;
        }
        return employees;
    }

    //обновить запись по id
    public void updateDataInCDB(int emp_id, String firstname, String lastname, String department, double salary){
        session.execute("UPDATE staff.employees set firstname = ?, lastname = ?, department = ?, salary = ? WHERE emp_id = ?",
                firstname, lastname, department, salary, emp_id);
    }

    //удалить запись по id
    public void deleteDataInCDB(int emp_id){
        session.execute("DELETE FROM staff.employees WHERE emp_id = ?", emp_id);
    }

    //вставить запись
    public void insertRecordToDB(int emp_id, String firstname, String lastname, String department, double salary){
        session.execute("INSERT INTO staff.employees (emp_id, firstname, lastname, department, salary) " + "VALUES(?, ?, ?, ?, ?) IF NOT EXISTS",
                emp_id, firstname, lastname,department, salary);
    }

    public void closeConnectToCDB(){
        //Закрываем соединение
        cluster.close();
        System.out.println("Закрываем соединение");
    }
}