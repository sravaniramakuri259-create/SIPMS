package exception;

public class DuplicateRollNumberException extends Exception {
	private static final long serialVersionUID = 1L;
    // ================= CONSTRUCTOR =================
    public DuplicateRollNumberException(String message) {
        super(message);
    }
}