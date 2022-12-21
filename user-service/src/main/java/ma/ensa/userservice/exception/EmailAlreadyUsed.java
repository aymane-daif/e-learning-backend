package ma.ensa.userservice.exception;

public class EmailAlreadyUsed extends Exception{
    public static final String MESSAGE = "This email address is already used";

    public EmailAlreadyUsed(String msg){
        super(msg);
    }

    public EmailAlreadyUsed(){
        super(MESSAGE);
    }
}
