package utils;

public class AccountException extends RuntimeException {
    private int errorCode;

    public AccountException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
