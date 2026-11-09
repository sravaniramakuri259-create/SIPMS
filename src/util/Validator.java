package util;

// ================= CLASS =================
public class Validator {

    // ================= METHOD: VALIDATE NAME =================
    public boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    // ================= METHOD: VALIDATE ROLL NUMBER =================
    public boolean isValidRollNumber(int rollNumber) {
        if (rollNumber <= 0) {
            return false;
        }
        return true;
    }

    // ================= METHOD: VALIDATE AGE =================
    public boolean isValidAge(int age) {
        if (age < 15 || age > 60) {
            return false;
        }
        return true;
    }

    // ================= METHOD: VALIDATE MARKS =================
    public boolean isValidMarks(double marks) {
        if (marks < 0 || marks > 100) {
            return false;
        }
        return true;
    }

    // ================= METHOD: VALIDATE ATTENDANCE =================
    public boolean isValidAttendance(int totalClasses, int classesAttended) {
        if (totalClasses < 0 || classesAttended < 0 || classesAttended > totalClasses) {
            return false;
        }
        return true;
    }
}