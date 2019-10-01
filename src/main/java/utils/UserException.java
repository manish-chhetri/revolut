package utils;

public class UserException extends RuntimeException {
    private int errorCode;

    public UserException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
