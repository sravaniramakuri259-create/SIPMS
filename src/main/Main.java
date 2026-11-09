package main;

import java.util.ArrayList;
import java.util.Scanner;

import exception.DuplicateRollNumberException;
import exception.InvalidMarksException;
import exception.StudentNotFoundException;
import model.Admin;
import model.Student;
import service.AttendanceManager;
import service.FeeManager;
import service.StatisticsManager;
import service.StudentManager;
import util.BackupHandler;
import util.FileHandler;
import util.Logger;
import util.Validator;

// ================= CLASS =================
public class Main {

    // ================= VARIABLES =================
    private static Scanner sc = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();
    private static FeeManager feeManager = new FeeManager();
    private static AttendanceManager attendanceManager = new AttendanceManager();
    private static StatisticsManager statisticsManager = new StatisticsManager();
    private static FileHandler fileHandler = new FileHandler();
    private static BackupHandler backupHandler = new BackupHandler();
    private static Logger logger = new Logger();
    private static Validator validator = new Validator();

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Admin admin = new Admin("Admin", 30, "Male", "9999999999", "admin", "admin123");

        System.out.println("===== SIPMS - STUDENT INFORMATION & PERFORMANCE MANAGEMENT SYSTEM =====");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!username.equals(admin.getUsername()) || !admin.checkPassword(password)) {
            System.out.println("Invalid username or password. Exiting.");
            return;
        }

        System.out.println("Login successful!");

        ArrayList<Student> loadedList = fileHandler.loadFromFile();
        studentManager.setStudentList(loadedList);

        boolean running = true;

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. View Department-wise List");
            System.out.println("7. Enter/Update Marks");
            System.out.println("8. View Rank List");
            System.out.println("9. Mark Attendance");
            System.out.println("10. Check Attendance Status");
            System.out.println("11. View Low Attendance List");
            System.out.println("12. Pay Fee");
            System.out.println("13. Check Fee Status");
            System.out.println("14. View Fee Due List");
            System.out.println("15. View Statistics");
            System.out.println("16. Take Backup");
            System.out.println("17. Restore Backup");
            System.out.println("18. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    studentManager.displayAllStudents();
                    break;
                case 6:
                    studentManager.displayDepartmentWise();
                    break;
                case 7:
                    enterMarks();
                    break;
                case 8:
                    studentManager.displayRankList();
                    break;
                case 9:
                    markAttendance();
                    break;
                case 10:
                    checkAttendanceStatus();
                    break;
                case 11:
                    attendanceManager.displayLowAttendanceList(studentManager.getStudentList());
                    break;
                case 12:
                    payFee();
                    break;
                case 13:
                    checkFeeStatus();
                    break;
                case 14:
                    feeManager.displayFeeDueList(studentManager.getStudentList());
                    break;
                case 15:
                    statisticsManager.displayStatistics(studentManager.getStudentList());
                    break;
                case 16:
                    backupHandler.takeBackup();
                    logger.logActivity("Backup taken");
                    break;
                case 17:
                    backupHandler.restoreBackup();
                    logger.logActivity("Backup restored");
                    break;
                case 18:
                    fileHandler.saveToFile(studentManager.getStudentList());
                    System.out.println("Data saved. Exiting SIPMS. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    // ================= METHOD: ADD STUDENT =================
    private static void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            if (!validator.isValidRollNumber(rollNumber)) {
                System.out.println("Invalid roll number. Must be a positive number.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            if (!validator.isValidName(name)) {
                System.out.println("Invalid name. Cannot be empty.");
                return;
            }

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            if (!validator.isValidAge(age)) {
                System.out.println("Invalid age. Must be between 15 and 60.");
                return;
            }

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Contact: ");
            String contact = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Year: ");
            int year = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            Student student = new Student(rollNumber, name, age, gender, contact, department, year, email);
            studentManager.addStudent(student);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Student added: Roll No " + rollNumber);

            System.out.println("Student added successfully!");
        } catch (DuplicateRollNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers where required.");
        }
    }

    // ================= METHOD: UPDATE STUDENT =================
    private static void updateStudent() {
        try {
            System.out.print("Enter Roll Number to update: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            Student existing = studentManager.searchStudent(rollNumber);

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter New Contact: ");
            String contact = sc.nextLine();

            System.out.print("Enter New Department: ");
            String department = sc.nextLine();

            System.out.print("Enter New Year: ");
            int year = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            Student updatedStudent = new Student(rollNumber, name, age, gender, contact, department, year, email);
            updatedStudent.setSubjectMarks(existing.getSubjectMarks());    updatedStudent.setTotalClasses(existing.getTotalClasses());
            updatedStudent.setClassesAttended(existing.getClassesAttended());
            updatedStudent.setFeePaid(existing.getFeePaid());
            updatedStudent.setFeeDue(existing.getFeeDue());

            studentManager.updateStudent(rollNumber, updatedStudent);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Student updated: Roll No " + rollNumber);

            System.out.println("Student updated successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers where required.");
        }
    }

    // ================= METHOD: DELETE STUDENT =================
    private static void deleteStudent() {
        try {
            System.out.print("Enter Roll Number to delete: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            studentManager.deleteStudent(rollNumber);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Student deleted: Roll No " + rollNumber);

            System.out.println("Student deleted successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // ================= METHOD: SEARCH STUDENT =================
    private static void searchStudent() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            Student student = studentManager.searchStudent(rollNumber);
            student.displayDetails();
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // ================= METHOD: ENTER / UPDATE MARKS =================
    private static void enterMarks() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            Student student = studentManager.searchStudent(rollNumber);

            double[] subjectMarks = new double[5];

            for (int i = 0; i < 5; i++) {
                System.out.print("Enter Marks for Subject " + (i + 1) + " (out of 100): ");
                double marks = Double.parseDouble(sc.nextLine());

                if (!validator.isValidMarks(marks)) {
                    throw new InvalidMarksException("Marks for Subject " + (i + 1) + " must be between 0 and 100.");
                }

                subjectMarks[i] = marks;
            }

            student.setSubjectMarks(subjectMarks);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Marks entered: Roll No " + rollNumber + " - Total: " + student.getTotalMarks());

            System.out.println("Marks updated successfully!");
            System.out.println("Total Marks: " + student.getTotalMarks() + " / 500");
            System.out.println("Percentage : " + student.getPercentage() + "%");
        } catch (StudentNotFoundException | InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }    // ================= METHOD: MARK ATTENDANCE =================
    private static void markAttendance() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Present or Absent? (P/A): ");
            String status = sc.nextLine();
            boolean isPresent = status.equalsIgnoreCase("P");

            attendanceManager.markAttendance(studentManager.getStudentList(), rollNumber, isPresent);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Attendance marked: Roll No " + rollNumber + " - " + (isPresent ? "Present" : "Absent"));

            System.out.println("Attendance marked successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // ================= METHOD: CHECK ATTENDANCE STATUS =================
    private static void checkAttendanceStatus() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            attendanceManager.checkAttendanceStatus(studentManager.getStudentList(), rollNumber);
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // ================= METHOD: PAY FEE =================
    private static void payFee() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Amount Paid: ");
            double amount = Double.parseDouble(sc.nextLine());

            feeManager.payFee(studentManager.getStudentList(), rollNumber, amount);
            fileHandler.saveToFile(studentManager.getStudentList());
            logger.logActivity("Fee paid: Roll No " + rollNumber + " - Amount: " + amount);

            System.out.println("Fee payment recorded successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // ================= METHOD: CHECK FEE STATUS =================
    private static void checkFeeStatus() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine());

            feeManager.checkFeeStatus(studentManager.getStudentList(), rollNumber);
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}