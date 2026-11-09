package model;

// ================= CLASS =================
public class Course {

    // ================= VARIABLES =================
    private String courseCode;
    private String courseName;
    private int credits;

    // ================= CONSTRUCTOR =================
    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    // ================= GETTER AND SETTER METHODS =================
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    // ================= METHOD =================
    public void displayCourseDetails() {
        System.out.println("Course Code : " + courseCode);
        System.out.println("Course Name : " + courseName);
        System.out.println("Credits     : " + credits);
    }
}