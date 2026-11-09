package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import exception.DuplicateRollNumberException;
import exception.StudentNotFoundException;
import model.Student;

// ================= CLASS =================
public class StudentManager {

    // ================= VARIABLES =================
    private ArrayList<Student> studentList;
    private HashSet<Integer> rollNumberSet;
    private HashMap<String, ArrayList<Student>> departmentMap;

    // ================= CONSTRUCTOR =================
    public StudentManager() {
        studentList = new ArrayList<Student>();
        rollNumberSet = new HashSet<Integer>();
        departmentMap = new HashMap<String, ArrayList<Student>>();
    }

    // ================= METHOD: ADD STUDENT =================
    public void addStudent(Student student) throws DuplicateRollNumberException {
        if (rollNumberSet.contains(student.getRollNumber())) {
            throw new DuplicateRollNumberException("Roll Number " + student.getRollNumber() + " already exists.");
        }
        studentList.add(student);
        rollNumberSet.add(student.getRollNumber());
    }

    // ================= METHOD: UPDATE STUDENT =================
    public void updateStudent(int rollNumber, Student updatedStudent) throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                studentList.set(i, updatedStudent);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: DELETE STUDENT =================
    public void deleteStudent(int rollNumber) throws StudentNotFoundException {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                studentList.remove(i);
                rollNumberSet.remove(rollNumber);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // ================= METHOD: SEARCH STUDENT =================
    public Student searchStudent(int rollNumber) throws StudentNotFoundException {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == rollNumber) {
                return studentList.get(i);
            }
        }
        throw new StudentNotFoundException("Student with Roll Number " + rollNumber + " not found.");
    }

    // ================= METHOD: DISPLAY ALL STUDENTS =================
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).displayDetails();
            System.out.println("-----------------------------");
        }
    }

    // ================= METHOD: GROUP BY DEPARTMENT =================
    public void groupByDepartment() {
        departmentMap.clear();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            String department = student.getDepartment();
            if (departmentMap.containsKey(department)) {
                departmentMap.get(department).add(student);
            } else {
                ArrayList<Student> newList = new ArrayList<Student>();
                newList.add(student);
                departmentMap.put(department, newList);
            }
        }
    }

    // ================= METHOD: DISPLAY DEPARTMENT WISE =================
    public void displayDepartmentWise() {
        groupByDepartment();
        if (departmentMap.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (String department : departmentMap.keySet()) {
            System.out.println("Department: " + department);
            ArrayList<Student> list = departmentMap.get(department);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).displayDetails();
                System.out.println("-----------------------------");
            }
        }
    }

    // ================= METHOD: SORT BY MARKS (RANK LIST) =================
    public void sortByMarks() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
            	return Double.compare(s2.getTotalMarks(), s1.getTotalMarks());            }
        });
    }

    // ================= METHOD: DISPLAY RANK LIST =================
    public void displayRankList() {
        sortByMarks();
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        int rank = 1;
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println("Rank " + rank + " - Roll Number: " + studentList.get(i).getRollNumber()
                    + ", Name: " + studentList.get(i).getName()
                    + ", Total Marks: " + studentList.get(i).getTotalMarks());   rank++;
        }
    }

    // ================= GETTER =================
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
        rollNumberSet.clear();
        for (int i = 0; i < studentList.size(); i++) {
            rollNumberSet.add(studentList.get(i).getRollNumber());
        }
    }
}