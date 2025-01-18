import java.util.*;

// Class to represent course details
class CourseInfo {
    String courseId;
    String courseTitle;
    String courseDescription;
    int courseCapacity;
    int courseEnrolled;
    String courseSchedule;

    public CourseInfo(String courseId, String courseTitle, String courseDescription, int courseCapacity, String courseSchedule) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseCapacity = courseCapacity;
        this.courseEnrolled = 0; // Initially no one is enrolled
        this.courseSchedule = courseSchedule;
    }

    // Check if slots are available
    public boolean hasAvailability() {
        return courseEnrolled < courseCapacity;
    }

    // Add a student to the course
    public void addStudent() {
        if (hasAvailability()) {
            courseEnrolled++;
        }
    }

    // Remove a student from the course
    public void removeStudent() {
        if (courseEnrolled > 0) {
            courseEnrolled--;
        }
    }

    @Override
    public String toString() {
        return courseId + ": " + courseTitle + " (" + courseSchedule + ")\nDescription: " + courseDescription + "\nCapacity: " + courseEnrolled + "/" + courseCapacity + "\n";
    }
}

// Class to represent student details
class StudentInfo {
    String studentUniqueId;
    String studentName;
    List<String> enrolledCourses;

    public StudentInfo(String studentUniqueId, String studentName) {
        this.studentUniqueId = studentUniqueId;
        this.studentName = studentName;
        this.enrolledCourses = new ArrayList<>(); // Start with no courses
    }

    // Add a course to the student's list
    public void addCourse(String courseId) {
        enrolledCourses.add(courseId);
    }

    // Remove a course from the student's list
    public void removeCourse(String courseId) {
        enrolledCourses.remove(courseId);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentUniqueId + ", Name: " + studentName + "\nEnrolled Courses: " + enrolledCourses + "\n";
    }
}

// Main class to handle the system
public class CourseDatabaseSystem {
    static Map<String, CourseInfo> allCourses = new HashMap<>();
    static Map<String, StudentInfo> allStudents = new HashMap<>();

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Adding some courses to start with
        allCourses.put("ENG101", new CourseInfo("ENG101", "English", "English Language", 30, "MWF 10-11AM"));
        allCourses.put("MAT101", new CourseInfo("MAT101", "Math", "Basic Mathematics", 25, "TTh 1-2:30PM"));

        // Adding some students to start with
        allStudents.put("S001", new StudentInfo("S001", "Rahul"));
        allStudents.put("S002", new StudentInfo("S002", "Priya"));

        while (true) {
            // Display menu
            System.out.println("\n=== Course Management System ===");
            System.out.println("1. Show All Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student Info");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int userChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear input buffer

            switch (userChoice) {
                case 1:
                    showAllCourses();
                    break;
                case 2:
                    registerCourse(inputScanner);
                    break;
                case 3:
                    dropCourse(inputScanner);
                    break;
                case 4:
                    displayStudentInfo(inputScanner);
                    break;
                case 5:
                    System.out.println("Thanks for using the system. Bye!");
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Oops! Invalid choice. Please try again.");
            }
        }
    }

    // Show all courses available
    private static void showAllCourses() {
        System.out.println("\nHere are the available courses:");
        for (CourseInfo course : allCourses.values()) {
            System.out.println(course);
        }
    }

    // Handle course registration
    private static void registerCourse(Scanner inputScanner) {
        System.out.print("Enter your Student ID: ");
        String studentId = inputScanner.nextLine();
        StudentInfo student = allStudents.get(studentId);

        if (student == null) {
            System.out.println("No student found with the given ID. Please check and try again.");
            return;
        }

        System.out.print("Enter the Course Code you want to register for: ");
        String courseId = inputScanner.nextLine();
        CourseInfo course = allCourses.get(courseId);

        if (course == null) {
            System.out.println("No course found with that code. Please check and try again.");
            return;
        }

        if (!course.hasAvailability()) {
            System.out.println("Sorry, the course is full. Please try another one.");
            return;
        }

        student.addCourse(courseId);
        course.addStudent();
        System.out.println("Great! You have been enrolled in " + course.courseTitle + ".");
    }

    // Handle dropping a course
    private static void dropCourse(Scanner inputScanner) {
        System.out.print("Enter your Student ID: ");
        String studentId = inputScanner.nextLine();
        StudentInfo student = allStudents.get(studentId);

        if (student == null) {
            System.out.println("No student found with the given ID. Please check and try again.");
            return;
        }

        System.out.print("Enter the Course Code you want to drop: ");
        String courseId = inputScanner.nextLine();

        if (!student.enrolledCourses.contains(courseId)) {
            System.out.println("You are not enrolled in this course. Nothing to drop.");
            return;
        }

        student.removeCourse(courseId);
        allCourses.get(courseId).removeStudent();
        System.out.println("You have successfully dropped the course " + courseId + ".");
    }

    // Display details of a student
    private static void displayStudentInfo(Scanner inputScanner) {
        System.out.print("Enter your Student ID: ");
        String studentId = inputScanner.nextLine();
        StudentInfo student = allStudents.get(studentId);

        if (student == null) {
            System.out.println("No student found with the given ID. Please check and try again.");
            return;
        }

        System.out.println("Here is the information we have:");
        System.out.println(student);
    }
}
