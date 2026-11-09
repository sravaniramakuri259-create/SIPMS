package exception;

public class InvalidMarksException extends Exception {
	private static final long serialVersionUID = 1L;
    // ================= CONSTRUCTOR =================
    public InvalidMarksException(String message) {
        super(message);
    }
}