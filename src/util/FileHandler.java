package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Student;

// ================= CLASS =================
public class FileHandler {

    // ================= VARIABLES =================
    private static final String FILE_NAME = "students.txt";

    // ================= METHOD: SAVE TO FILE =================
    public void saveToFile(ArrayList<Student> studentList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (int i = 0; i < studentList.size(); i++) {
                Student student = studentList.get(i);
                double[] subjectMarks = student.getSubjectMarks();

                String line = student.getRollNumber() + "," + student.getName() + "," + student.getAge() + ","
                        + student.getGender() + "," + student.getContact() + "," + student.getDepartment() + ","
                        + student.getYear() + "," + student.getEmail() + ","
                        + subjectMarks[0] + "," + subjectMarks[1] + "," + subjectMarks[2] + ","
                        + subjectMarks[3] + "," + subjectMarks[4] + ","
                        + student.getTotalClasses() + "," + student.getClassesAttended() + ","
                        + student.getFeePaid() + "," + student.getFeeDue();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    // ================= METHOD: LOAD FROM FILE =================
    public ArrayList<Student> loadFromFile() {
        ArrayList<Student> studentList = new ArrayList<Student>();

        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                int rollNumber = Integer.parseInt(values[0]);
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                String gender = values[3];
                String contact = values[4];
                String department = values[5];
                int year = Integer.parseInt(values[6]);
                String email = values[7];

                double[] subjectMarks = new double[5];
                subjectMarks[0] = Double.parseDouble(values[8]);
                subjectMarks[1] = Double.parseDouble(values[9]);
                subjectMarks[2] = Double.parseDouble(values[10]);
                subjectMarks[3] = Double.parseDouble(values[11]);
                subjectMarks[4] = Double.parseDouble(values[12]);

                int totalClasses = Integer.parseInt(values[13]);
                int classesAttended = Integer.parseInt(values[14]);
                double feePaid = Double.parseDouble(values[15]);
                double feeDue = Double.parseDouble(values[16]);

                Student student = new Student(rollNumber, name, age, gender, contact, department, year, email);
                student.setSubjectMarks(subjectMarks);
                student.setTotalClasses(totalClasses);
                student.setClassesAttended(classesAttended);
                student.setFeePaid(feePaid);
                student.setFeeDue(feeDue);

                studentList.add(student);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        return studentList;
    }
}