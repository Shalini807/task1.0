package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Main result = new Main();
        Connection connection = result.sqlConnect();
        result.addEmployee(connection);
//        result.getEmployee(connection);
    }
    public Connection sqlConnect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Welcome@123");
        return connection;
    }
    public PreparedStatement getEmployee(Connection connection) throws SQLException {
        List<Object> emp_details = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employeeDetails WHERE id = ?");
        statement.setInt(1, 1);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            emp_details.add(id);
            emp_details.add(name);
            emp_details.add(address);
            System.out.println("employee Details:" + emp_details);
        }
        return statement;
    }

    public PreparedStatement addEmployee(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into employeeDetails (id,name,address) values (?,?,?);");
        preparedStatement.setInt(1, 4);
        preparedStatement.setString(2, "raju");
        preparedStatement.setString(3, "tyuiop");
        preparedStatement.executeUpdate();
        System.out.println("Data Inserted");
        return preparedStatement;
    }

}