package mvc.com.exceptions;

public class ValidationException extends RuntimeException {
    String message;
    public ValidationException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Validation problem. Error occurred: " + message;
    }
}
