package ex4_mysql.dao;

import ex4_mysql.Course;
import ex4_mysql.DatabaseManager;
import ex4_mysql.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public static List<Course> getAllCourses() {

        String query = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();


        try {
            try (Statement statement = DatabaseManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String code = resultSet.getString("code");
                    String name = resultSet.getString("name");
                    int credit = resultSet.getInt("credit");

                    Course course = new Course(id, code, name, credit);
                    courses.add(course);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    public static void saveCourse(Course course) {

        String query = String.format("INSERT INTO courses (code, name, credit) VALUES ('%s', '%s', %d)",
                course.getCode(),
                course.getName(),
                course.getCredits());

        DatabaseManager.executeUpdate(query);
        System.out.println("Saved course successfully");

    }

    public static void deleteCourse(long id) {

        String query = String.format("DELETE FROM courses WHERE id = %d", id);
        int rowEffected = DatabaseManager.executeUpdate(query);

        if (rowEffected > 0) {
            System.out.println("Delete course successfully!");
        } else {
            System.out.println("No course found with id: " + id);
        }
    }

    public static Course getCourseById(long courseId) {

        String query = String.format("SELECT * FROM courses WHERE id = %d", courseId);
        Course course = null;
        try {
            try (Statement statement = DatabaseManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String code = resultSet.getString("code");
                    String name = resultSet.getString("name");
                    int credit = resultSet.getInt("credit");

                    course = new Course(id, code, name, credit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return course;
    }
}
