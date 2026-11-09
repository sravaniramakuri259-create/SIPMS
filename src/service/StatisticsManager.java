package service;

import java.util.ArrayList;

import model.Student;

// ================= CLASS =================
public class StatisticsManager {

    // ================= METHOD: CALCULATE AVERAGE MARKS =================
    public double calculateAverageMarks(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < studentList.size(); i++) {
        	total = total + studentList.get(i).getTotalMarks();        }
        return total / studentList.size();
    }

    // ================= METHOD: FIND HIGHEST MARKS STUDENT =================
    public Student findHighestMarksStudent(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            return null;
        }
        Student highest = studentList.get(0);
        for (int i = 1; i < studentList.size(); i++) {
        	if (studentList.get(i).getTotalMarks() > highest.getTotalMarks()) {   }
        }
        return highest;
    }

    // ================= METHOD: FIND LOWEST MARKS STUDENT =================
    public Student findLowestMarksStudent(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            return null;
        }
        Student lowest = studentList.get(0);
        for (int i = 1; i < studentList.size(); i++) {
        	if (studentList.get(i).getTotalMarks() < lowest.getTotalMarks()) {
            }
        }
        return lowest;
    }

    // ================= METHOD: COUNT STUDENTS BY DEPARTMENT =================
    public int countStudentsByDepartment(ArrayList<Student> studentList, String department) {
        int count = 0;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getDepartment().equalsIgnoreCase(department)) {
                count++;
            }
        }
        return count;
    }

    // ================= METHOD: DISPLAY STATISTICS =================
    public void displayStatistics(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        double average = calculateAverageMarks(studentList);
        Student highest = findHighestMarksStudent(studentList);
        Student lowest = findLowestMarksStudent(studentList);

        System.out.println("Total Students     : " + studentList.size());
        System.out.println("Average Marks       : " + average);
        System.out.println("Highest Marks       : " + highest.getTotalMarks() + " (Roll Number: " + highest.getRollNumber() + ", Name: " + highest.getName() + ")");
        System.out.println("Lowest Marks        : " + lowest.getTotalMarks() + " (Roll Number: " + lowest.getRollNumber() + ", Name: " + lowest.getName() + ")");
    }
}