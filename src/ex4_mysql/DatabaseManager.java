package ex4_mysql;

import java.sql.*;

public class DatabaseManager {
    static String DB_URL = "jdbc:mysql://localhost:3306/simpledb";
    static String DB_USER = "root";
    static String DB_PASSWORD = "5599";
    private static Connection connection;

    public static void connectDB() {
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connect successfully!");
            // init tables
            initTableStudent();
            initTableCourse();
            initTableEnrollment();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void initTableStudent() {

        String query = "CREATE TABLE IF NOT EXISTS students(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "age INT NOT NULL" +
                ");";

        executeUpdate(query);

    }

    private static void initTableCourse() {

        String query = "CREATE TABLE IF NOT EXISTS courses (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "code VARCHAR(255) NOT NULL," +
                "name VARCHAR(255) NOT NULL," +
                "credit INT NOT NULL" +
                ");";

        executeUpdate(query);
    }


    private static void initTableEnrollment() {
        String query = "CREATE TABLE IF NOT EXISTS enrollments (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT NOT NULL," +
                "course_id INT NOT NULL," +
                "grade INT NOT NULL," +
                "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE," +
                "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE   " +
                ");";

        executeUpdate(query);
    }




    public static int executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
