package exception;

public class InvalidAttendanceException extends Exception {
	private static final long serialVersionUID = 1L;
    // ================= CONSTRUCTOR =================
    public InvalidAttendanceException(String message) {
        super(message);
    }
}