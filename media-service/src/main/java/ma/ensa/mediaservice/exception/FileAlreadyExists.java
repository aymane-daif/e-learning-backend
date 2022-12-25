package ma.ensa.mediaservice.exception;

public class FileAlreadyExists extends Exception{
    public static final String MESSAGE = "This file path is already exists";
    public FileAlreadyExists(String msg) {
        super(msg);
    }
    public FileAlreadyExists() {
        super(MESSAGE);
    }
}
