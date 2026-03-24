package com.student;

import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    static Connection con = DBConnection.getConnection();
    static Scanner sc = new Scanner(System.in);

    public static void addStudent() {
        try {
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Course: ");
            String course = sc.next();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students(name, age, course) VALUES (?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);

            ps.executeUpdate();
            System.out.println("Student Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewStudents() {
        try {
            String query = "SELECT * FROM students";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Student List ---");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String course = rs.getString("course");

                System.out.println(id + " | " + name + " | " + age + " | " + course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewStudent() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            System.out.println("ID  Name  Age  Course");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String course = rs.getString("course");

                System.out.println("ID: " + id + 
                                   " | Name: " + name + 
                                   " | Age: " + age + 
                                   " | Course: " + course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM students WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            System.out.print("New Name: ");
            String name = sc.next();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE students SET name=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void searchStudent() {
        try {
            System.out.print("Enter student name to search: ");
            String name = sc.next();

            String query = "SELECT * FROM students WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String course = rs.getString("course");

                System.out.println("ID: " + id +
                                   " | Name: " + name +
                                   " | Age: " + age +
                                   " | Course: " + course);
            }

            if (!found) {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	

        if (!Login.loginUser()) {
            return;
            
        }
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

        while (true) {
            System.out.println("\n1. Add");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.println("6. Search");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.exit(0);break;
                case 6: searchStudent();
            }
        }
        
    }
}