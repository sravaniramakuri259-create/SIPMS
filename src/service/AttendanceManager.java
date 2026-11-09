package service;

import java.util.ArrayList;

import exception.InvalidAttendanceException;
import exception.StudentNotFoundException;
import model.Student;

// ================= CLASS =================
public class AttendanceManager {
	

    // ================= METHOD: MARK ATTENDANCE =================
    public void markAttendance(ArrayList<Student> studentList, int rollNumber, boolean isPresent)
            throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                Student student = studentList.get(i);
                student.setTotalClasses(student.getTotalClasses() + 1);
                if (isPresent) {
                    student.setClassesAttended(student.getClassesAttended() + 1);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: CHECK ATTENDANCE STATUS =================
    public void checkAttendanceStatus(ArrayList<Student> studentList, int rollNumber)
            throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                Student student = studentList.get(i);
                double percentage = student.calculateAttendancePercentage();
                System.out.println("Roll Number      : " + student.getRollNumber());
                System.out.println("Name             : " + student.getName());
                System.out.println("Total Classes    : " + student.getTotalClasses());
                System.out.println("Classes Attended : " + student.getClassesAttended());
                System.out.println("Attendance %     : " + percentage);
                if (percentage < 75) {
                    System.out.println("WARNING: Attendance is below 75%.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: DISPLAY LOW ATTENDANCE LIST =================
    public void displayLowAttendanceList(ArrayList<Student> studentList) {
        boolean anyLow = false;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            double percentage = student.calculateAttendancePercentage();
            if (percentage < 75 && student.getTotalClasses() > 0) {
                System.out.println("Roll Number : " + student.getRollNumber()
                        + ", Name: " + student.getName()
                        + ", Attendance %: " + percentage);
                anyLow = true;
            }
        }
        if (!anyLow) {
            System.out.println("No students with low attendance.");
        }
    }
}