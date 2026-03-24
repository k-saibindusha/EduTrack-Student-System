package com.student;

import java.sql.*;
import java.util.Scanner;

public class Login {

    public static boolean loginUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String uname = sc.next();
        System.out.print("Enter password: ");
        String pass = sc.next();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, uname);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.println("Login Successful ✅");
                return true;
            } else {
                System.out.println("Invalid Credentials!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}