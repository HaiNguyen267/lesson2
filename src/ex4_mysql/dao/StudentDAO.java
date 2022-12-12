package ex4_mysql.dao;

import ex4_mysql.Course;
import ex4_mysql.DatabaseManager;
import ex4_mysql.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> getAllStudents() {

        String query = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();


        try {
            try (Statement statement = DatabaseManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String code = resultSet.getString("name");
                    String name = resultSet.getString("email");
                    int credit = resultSet.getInt("age");

                    Student student = new Student(id, code, name, credit);
                    students.add(student);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static void deleteStudent(long id) {
        String query = "DELETE FROM students WHERE id = " + id;
        int rowEffected = DatabaseManager.executeUpdate(query);

        if (rowEffected > 0) {
            System.out.println("Delete student successfully!");
        } else {
            System.out.println("No student found with id: " + id);
        }
    }

    public static void saveStudent(Student student) {
        String query = String.format("INSERT INTO students (name, email, age) VALUES ('%s', '%s', %d)",
                student.getName(),
                student.getEmail(),
                student.getAge());

        DatabaseManager.executeUpdate(query);
        System.out.println("Saved student successfully");
    }

    public static Student getStudentById(long studentId) {
        String query = "SELECT * FROM students WHERE id = " + studentId;
        Student student = null;
        try {
            try (Statement statement = DatabaseManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    int age = resultSet.getInt("age");

                    student = new Student(id, name, email, age);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
