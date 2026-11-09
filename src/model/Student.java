package model;

// ================= CLASS =================
public class Student extends Person {

    // ================= CONSTANT =================
    private static final int TOTAL_SUBJECTS = 5;

    // ================= VARIABLES =================
    private int rollNumber;
    private String department;
    private int year;
    private String email;
    private double[] subjectMarks;
    private double totalMarks;
    private double percentage;
    private int totalClasses;
    private int classesAttended;
    private double feePaid;
    private double feeDue;

    // ================= CONSTRUCTOR =================
    public Student(int rollNumber, String name, int age, String gender, String contact,
            String department, int year, String email) {
        super(name, age, gender, contact);
        this.rollNumber = rollNumber;
        this.department = department;
        this.year = year;
        this.email = email;
        this.subjectMarks = new double[TOTAL_SUBJECTS];
        this.totalMarks = 0;
        this.percentage = 0;
        this.totalClasses = 0;
        this.classesAttended = 0;
        this.feePaid = 0;
        this.feeDue = 0;
    }

    // ================= GETTER AND SETTER METHODS =================
    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double[] getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(double[] subjectMarks) {
        this.subjectMarks = subjectMarks;
        calculateTotalAndPercentage();
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getClassesAttended() {
        return classesAttended;
    }

    public void setClassesAttended(int classesAttended) {
        this.classesAttended = classesAttended;
    }

    public double getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(double feePaid) {
        this.feePaid = feePaid;
    }

    public double getFeeDue() {
        return feeDue;
    }

    public void setFeeDue(double feeDue) {
        this.feeDue = feeDue;
    }

    // ================= METHOD: SET ONE SUBJECT MARK =================
    public void setSubjectMark(int subjectIndex, double mark) {
        subjectMarks[subjectIndex] = mark;
        calculateTotalAndPercentage();
    }

    // ================= METHOD: CALCULATE TOTAL AND PERCENTAGE =================
    private void calculateTotalAndPercentage() {
        double sum = 0;
        for (int i = 0; i < subjectMarks.length; i++) {
            sum = sum + subjectMarks[i];
        }
        totalMarks = sum;
        percentage = (totalMarks / (subjectMarks.length * 100)) * 100;
    }

    // ================= METHOD: CALCULATE ATTENDANCE PERCENTAGE =================
    public double calculateAttendancePercentage() {
        if (totalClasses == 0) {
            return 0;
        }
        return ((double) classesAttended / totalClasses) * 100;
    }

    // ================= DISPLAY METHOD (OVERRIDDEN) =================
    @Override
    public void displayDetails() {
        System.out.println("Roll Number   : " + rollNumber);
        System.out.println("Name          : " + getName());
        System.out.println("Age           : " + getAge());
        System.out.println("Gender        : " + getGender());
        System.out.println("Contact       : " + getContact());
        System.out.println("Department    : " + department);
        System.out.println("Year          : " + year);
        System.out.println("Email         : " + email);
        for (int i = 0; i < subjectMarks.length; i++) {
            System.out.println("Subject " + (i + 1) + " Marks : " + subjectMarks[i]);
        }
        System.out.println("Total Marks   : " + totalMarks + " / " + (subjectMarks.length * 100));
        System.out.println("Percentage    : " + percentage + "%");
        System.out.println("Attendance %  : " + calculateAttendancePercentage());
        System.out.println("Fee Paid      : " + feePaid);
        System.out.println("Fee Due       : " + feeDue);
    }
}