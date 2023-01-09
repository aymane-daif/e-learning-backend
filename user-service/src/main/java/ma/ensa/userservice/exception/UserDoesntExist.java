package ma.ensa.userservice.exception;

public class UserDoesntExist extends Exception{

    private final static String MESSAGE = "This user doesn't exist";

    public UserDoesntExist(){
        super(MESSAGE);
    }

}
