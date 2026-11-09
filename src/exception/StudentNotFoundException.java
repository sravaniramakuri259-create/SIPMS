package exception;

public class StudentNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
    // ================= CONSTRUCTOR =================
    public StudentNotFoundException(String message) {
        super(message);
    }
}