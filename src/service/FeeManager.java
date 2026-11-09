package service;

import java.util.ArrayList;

import exception.StudentNotFoundException;
import model.Student;

// ================= CLASS =================
public class FeeManager {

    // ================= METHOD: PAY FEE =================
    public void payFee(ArrayList<Student> studentList, int rollNumber, double amount) throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                Student student = studentList.get(i);
                student.setFeePaid(student.getFeePaid() + amount);
                student.setFeeDue(student.getFeeDue() - amount);
                if (student.getFeeDue() < 0) {
                    student.setFeeDue(0);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: SET TOTAL FEE (SETS INITIAL DUE) =================
    public void setTotalFee(ArrayList<Student> studentList, int rollNumber, double totalFee) throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                Student student = studentList.get(i);
                student.setFeeDue(totalFee - student.getFeePaid());
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: CHECK FEE STATUS =================
    public void checkFeeStatus(ArrayList<Student> studentList, int rollNumber) throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                Student student = studentList.get(i);
                System.out.println("Roll Number : " + student.getRollNumber());
                System.out.println("Name        : " + student.getName());
                System.out.println("Fee Paid    : " + student.getFeePaid());
                System.out.println("Fee Due     : " + student.getFeeDue());
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: DISPLAY FEE DUE LIST =================
    public void displayFeeDueList(ArrayList<Student> studentList) {
        boolean anyDue = false;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getFeeDue() > 0) {
                System.out.println("Roll Number : " + student.getRollNumber()
                        + ", Name: " + student.getName()
                        + ", Fee Due: " + student.getFeeDue());
                anyDue = true;
            }
        }
        if (!anyDue) {
            System.out.println("No students have pending fee due.");
        }
    }
}