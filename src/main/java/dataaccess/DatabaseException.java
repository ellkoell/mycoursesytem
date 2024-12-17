package dataaccess;

public class DatabaseException extends RuntimeException {

    //eigene Exception mit spezifischer Message für mögliche Exceptions
    public DatabaseException(String message) {
        super(message);
    }
}
