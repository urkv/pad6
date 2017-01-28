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
public class CassandraDatabase {
    private Cluster cluster;
    private Session session;

    private static final String KEY_SPACE = "staff";
    private static final String EMPLOYEE_TABLE = "employees";
    private static final String EMPLOYEE_ID = "emp_id";
    private static final String EMPLOYEE_FIRST_NAME = "first_name";
    private static final String EMPLOYEE_LAST_NAME = "last_name";
    private static final String EMPLOYEE_DEPARTMENT = "department";
    private static final String EMPLOYEE_SALARY = "salary";

    public void openConnectToCDB(final String address, final int port){
        //открываем соединение с кластером
        this.cluster = Cluster.builder().addContactPoint(address).withPort(port).build();
        this.session = cluster.connect();
        //System.out.println("Открываем соединение с кластером");
    }

    public void openConnectToCDB(){
        //открываем соединение с кластером
        this.cluster = Cluster.builder().addContactPoint("localhost").withPort(9042).build();
        this.session = cluster.connect();
        //System.out.println("Открываем соединение с кластером");
    }

    public void closeConnectToCDB(){
        //Закрываем соединение
        cluster.close();
        System.out.println("Закрываем соединение");
    }
    //подготовка БД
    void prepareCDB(){
        String createKeyspaceQuery = "CREATE KEYSPACE IF NOT EXISTS " + KEY_SPACE +
                " WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";
        String createTableQuery = "CREATE TABLE IF NOT EXISTS "+ EMPLOYEE_TABLE + "(" +
                EMPLOYEE_ID +" int PRIMARY KEY, "+
                EMPLOYEE_FIRST_NAME +" text, "+
                EMPLOYEE_LAST_NAME +" text, "+
                EMPLOYEE_DEPARTMENT +" text, "+
                EMPLOYEE_SALARY +" double" +
                ");";
        //создание пространства ключей
        session.execute(createKeyspaceQuery);
        System.out.println("Пространство ключей создано");
        //используем пространство ключей
        session.execute("USE " + KEY_SPACE);
        //создание таблицы сотрудников
        session.execute(createTableQuery);
        System.out.println("Таблица создана");
    }

    //добавление записей в таблицу
    public void fillEmployees(){
        String insertInto = "INSERT INTO "+ EMPLOYEE_TABLE +" ("+ EMPLOYEE_ID +", "+ EMPLOYEE_FIRST_NAME +", "+ EMPLOYEE_LAST_NAME +", "+ EMPLOYEE_DEPARTMENT +", "+ EMPLOYEE_SALARY +") ";
        String insertValues1 = "VALUES(1, 'Serj', 'Litovchenko', 'Administration', 100000) IF NOT EXISTS;";
        String insertValues2 = "VALUES(2, 'Andrew', 'Dixon', 'Product Development', 70000) IF NOT EXISTS;";
        String insertValues3 = "VALUES(3, 'Anthony', 'Chor', 'IS', 40000) IF NOT EXISTS;";
        String insertValues4 = "VALUES(4, 'Asley', 'Larsen', 'Operations', 50000) IF NOT EXISTS;";
        String insertValues5 = "VALUES(5, 'Beth', 'Silverberg', 'Customer Service', 45000) IF NOT EXISTS;";
        String insertValues6 = "VALUES(6, 'Clair', 'Hector', 'Product Development', 50000) IF NOT EXISTS;";
        String insertValues7 = "VALUES(7, 'David', 'Jaffe', 'Product Development', 60000) IF NOT EXISTS;";
        String insertValues8 = "VALUES(8, 'Denise', 'Smith', 'Product Development', 55000) IF NOT EXISTS;";
        String insertValues9 = "VALUES(9, 'Eric', 'Lang', 'Product Development', 65000) IF NOT EXISTS;";
        String insertValues10 = "VALUES(10, 'Fukiko', 'Ogisu', 'Operations', 45000) IF NOT EXISTS;";
        String insertValues11 = "VALUES(11, 'Helmut', 'Horrig', 'Product Development', 35000) IF NOT EXISTS;";
        String insertValues12 = "VALUES(12, 'Jae', 'Pak', 'Operations', 45000) IF NOT EXISTS;";
        String insertValues13 = "VALUES(13, 'Jeffrey', 'Pira', 'Human Resources', 40000) IF NOT EXISTS;";
        String insertValues14 = "VALUES(14, 'John', 'Tippet', 'Customer Services', 50000) IF NOT EXISTS;";
        String insertValues15 = "VALUES(15, 'Jolie', 'Lenehen', 'Finance', 55000) IF NOT EXISTS;";
        String insertValues16 = "VALUES(16, 'Jon', 'Grande', 'Product Development', 33000) IF NOT EXISTS;";
        String insertValues17 = "VALUES(17, 'Jonathan', 'Yound', 'Product Development', 37000) IF NOT EXISTS;";
        String insertValues18 = "VALUES(18, 'Kim', 'Abercrombe', 'Human Resources', 45000) IF NOT EXISTS;";
        String insertValues19 = "VALUES(19, 'John', 'Colon', 'Customer Service', 48000) IF NOT EXISTS;";
        String insertValues20 = "VALUES(20, 'Deborah', 'Lee', 'Product Development', 63000) IF NOT EXISTS;";

        String insertQuery[] = {
                insertInto + insertValues1,  insertInto +  insertValues2,
                insertInto + insertValues3,  insertInto +  insertValues4,
                insertInto + insertValues5,  insertInto +  insertValues6,
                insertInto + insertValues7,  insertInto +  insertValues8,
                insertInto + insertValues9,  insertInto +  insertValues10,
                insertInto + insertValues11, insertInto + insertValues12,
                insertInto + insertValues13, insertInto + insertValues14,
                insertInto + insertValues15, insertInto + insertValues16,
                insertInto + insertValues17, insertInto + insertValues18,
                insertInto + insertValues19, insertInto + insertValues20 };
        //добавляем записи
        session.execute("USE " + KEY_SPACE);
        for (int i = 0; i < 20; i++){
            session.execute(insertQuery[i]);
        }
    }

    //вывод записей из таблицы
    public void showDataFromEmployees(){
        //получаем записи таблицы
        session.execute("USE " + KEY_SPACE);
        ResultSet query_result = session.execute("SELECT * FROM " + EMPLOYEE_TABLE);
        System.out.println("Table "+ EMPLOYEE_TABLE+ ":");
        for (Row row : query_result) {
            System.out.format("%d %s %s %s %f\n",
                    row.getInt(EMPLOYEE_ID),
                    row.getString(EMPLOYEE_FIRST_NAME),
                    row.getString(EMPLOYEE_LAST_NAME),
                    row.getString(EMPLOYEE_DEPARTMENT),
                    row.getDouble(EMPLOYEE_SALARY));
        }
    }

    //возвращает список сотрудников из таблици
    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        //получаем записи таблицы
        session.execute("USE " + KEY_SPACE);
        ResultSet query_result = session.execute("SELECT * FROM " + EMPLOYEE_TABLE);
        for (Row row : query_result) {
            employees.add(
                    new Employee(
                            row.getInt(EMPLOYEE_ID),
                            row.getString(EMPLOYEE_FIRST_NAME),
                            row.getString(EMPLOYEE_LAST_NAME),
                            row.getString(EMPLOYEE_DEPARTMENT),
                            row.getDouble(EMPLOYEE_SALARY))
                    );
        }
        return employees;
    }

    //обновить запись по id
    public void updateEmployee(int emp_id, String firstName, String lastName, String department, double salary){
        session.execute("USE " + KEY_SPACE);
        session.execute("UPDATE "+EMPLOYEE_TABLE+" set " +
                        EMPLOYEE_FIRST_NAME +" = ?, "+
                        EMPLOYEE_LAST_NAME +" = ?, "+
                        EMPLOYEE_DEPARTMENT +" = ?, "+
                        EMPLOYEE_SALARY +" = ? " +
                        "WHERE "+ EMPLOYEE_ID +" = ?",
                        firstName, lastName, department, salary, emp_id);
    }

    //удалить запись по id
    public void deleteEmployee(int id){
        session.execute("USE " + KEY_SPACE);
        session.execute("DELETE FROM "+EMPLOYEE_TABLE+" WHERE "+ EMPLOYEE_ID +" = ?", id);
    }

    //вставить запись
    public void insertEmployee(int emp_id, String firstName, String lastName, String department, double salary){
        session.execute("USE " + KEY_SPACE);
        session.execute("INSERT INTO "+EMPLOYEE_TABLE+" ("+
                        EMPLOYEE_ID +", "+
                        EMPLOYEE_FIRST_NAME +", "+
                        EMPLOYEE_LAST_NAME +", "+
                        EMPLOYEE_DEPARTMENT +", "+
                        EMPLOYEE_SALARY
                        +") " +
                        "VALUES(?, ?, ?, ?, ?) IF NOT EXISTS",
                        emp_id, firstName, lastName,department, salary);
    }
}