package ex4_mysql.dao;

import ex4_mysql.Course;
import ex4_mysql.DatabaseManager;
import ex4_mysql.Enrollment;
import ex4_mysql.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public static void saveEnrollment(Enrollment enrollment) {
        String query = String.format("INSERT INTO enrollments (student_id, course_id, grade) VALUES (%d, %d, %d)",
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getGrade());

        DatabaseManager.executeUpdate(query);
        System.out.println("Enrollment saved successfully");
    }

    public static List<Enrollment> getAllEnrollments() {
        String query = "SELECT * FROM enrollments";
        List<Enrollment> enrollments = new ArrayList<>();


        try {
            try (Statement statement = DatabaseManager.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    long studentId = resultSet.getLong("student_id");
                    long courseId = resultSet.getLong("course_id");
                    int grade = resultSet.getInt("grade");


                    Student student = StudentDAO.getStudentById(studentId);
                    Course course = CourseDAO.getCourseById(courseId);

                    Enrollment enrollment = new Enrollment(id, student, course, grade);
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return enrollments;
    }
}
