package com.student;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            // MySQL URL
            String url = "jdbc:mysql://localhost:3306/student_db";

            // username & password
            String user = "root";
            String password = "1234"; // keep empty if no password

            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to Database ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}