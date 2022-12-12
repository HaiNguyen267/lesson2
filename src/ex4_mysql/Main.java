package ex4_mysql;

import ex1_textfile.Action;
import ex1_textfile.Menu;
import ex4_mysql.dao.CourseDAO;
import ex4_mysql.dao.EnrollmentDAO;
import ex4_mysql.dao.StudentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseManager.connectDB();

        Menu mainMenu = createMainMenu();
        mainMenu.run();
    }

    private static Menu createMainMenu() {
        Menu menu = new Menu("Main Menu");

        Menu studentMenu = createStudentMenu();
        Menu courseMenu = createCourseMenu();
        Menu enrollmentMenu = createEnrollmentMenu();


        menu.addItems(studentMenu);
        menu.addItems(courseMenu);
        menu.addItems(enrollmentMenu);

        return menu;

    }

    private static Menu createStudentMenu() {

        Menu studentMenu = new Menu("Student Menu");

        Action listAllStudents = new Action("List all students") {
            @Override
            public void run() {
                List<Student> students = StudentDAO.getAllStudents();

                if (students.isEmpty()) {
                    System.out.println("No students found");
                } else {
                    System.out.println(String.format("%-15s | %-15s | %-15s | %-15s",
                            "id",
                            "name",
                            "email",
                            "age")
                    );

                    for (Student student : students) {
                        System.out.println(String.format("%-15d | %-15s | %-15s | %-15d",
                                student.getId(),
                                student.getName(),
                                student.getEmail(),
                                student.getAge())
                        );
                    }
                }
            }
        };

        Action addStudent = new Action("Add a student") {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter student's name: ");
                String name = sc.nextLine();

                System.out.print("Enter student's email: ");
                String email = sc.nextLine();

                System.out.print("Enter student's age: ");
                int age = Integer.parseInt(sc.nextLine());

                Student student = new Student(name, email, age);
                StudentDAO.saveStudent(student);
            }
        };


    Action deleteStudent = new Action("Delete a student") {
        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter student's id: ");
            long id = sc.nextLong();

            StudentDAO.deleteStudent(id);
        }
    };

        studentMenu.addItems(listAllStudents);
        studentMenu.addItems(addStudent);
        studentMenu.addItems(deleteStudent);

        return studentMenu;
}

    private static Menu createCourseMenu() {

        Menu courseMenu = new Menu("Course Menu");
        Action listAllCourses = new Action("List all courses") {
            @Override
            public void run() {
                List<Course> courses = CourseDAO.getAllCourses();

                if (courses.isEmpty()) {
                    System.out.println("No courses found");
                } else {
                    System.out.println(String.format("%-15s | %-15s | %-35s | %-15s",
                            "id",
                            "code",
                            "name",
                            "credits")
                    );

                    for (Course course : courses) {
                        System.out.println(String.format("%-15d | %-15s | %-35s | %-15d",
                                course.getId(),
                                course.getCode(),
                                course.getName(),
                                course.getCredits()
                        ));
                    }
                }
            }
        };

        Action addCourse = new Action("Add Course") {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter course code: ");
                String code = sc.nextLine();

                System.out.print("Enter course name: ");
                String name = sc.nextLine();

                System.out.print("Enter course credits: ");
                int credits = Integer.parseInt(sc.nextLine());

                Course course = new Course(code, name, credits);
                CourseDAO.saveCourse(course);
            }
        };

        Action deleteCourse = new Action("Delete Course") {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter course id: ");
                long id = Long.parseLong(sc.nextLine());

                CourseDAO.deleteCourse(id);
            }
        };

        courseMenu.addItems(listAllCourses);
        courseMenu.addItems(addCourse);
        courseMenu.addItems(deleteCourse);
        return courseMenu;
    }

    private static Menu createEnrollmentMenu() {

        Menu enrollmentMenu = new Menu("Enrollment menu");

        Action addEnrollment = new Action("Add enrollment") {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);

                long studentId = getStudentIdInput();
                long courseId = getCourseIdInput();

                Student student = StudentDAO.getStudentById(studentId);
                Course course = CourseDAO.getCourseById(courseId);

                if (student == null) {
                    System.out.println("Student not found");
                    return;
                }

                if (course == null) {
                    System.out.println("Course not found");
                    return;
                }

                System.out.print("Enter grade: ");
                int grade = Integer.parseInt(sc.nextLine());

                Enrollment enrollment = new Enrollment(student, course, grade);
                EnrollmentDAO.saveEnrollment(enrollment);
            }

            private long getCourseIdInput() {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter course id: ");
                return Long.parseLong(sc.nextLine());
            }

            private long getStudentIdInput() {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter student id: ");
                return Long.parseLong(sc.nextLine());
            }

        };

        Action listAllEnrollments = new Action("List all enrollments") {
            @Override
            public void run() {
                List<Enrollment> list = EnrollmentDAO.getAllEnrollments();

                if (list.isEmpty()) {
                    System.out.println("No enrollments found");
                } else {
                    System.out.println(String.format("%-15s | %-15s | %-15s | %-15s",
                            "enrollment_id",
                            "student_id",
                            "course_id",
                            "grade")
                    );

                    for (Enrollment enrollment : list) {
                        System.out.println(String.format("%-15d | %-15d | %-15d | %-15d",
                                enrollment.getId(),
                                enrollment.getStudent().getId(),
                                enrollment.getCourse().getId(),
                                enrollment.getGrade())
                        );
                    }
                }
            }
        };


        enrollmentMenu.addItems(listAllEnrollments);
        enrollmentMenu.addItems(addEnrollment);

        return enrollmentMenu;
    }


}
