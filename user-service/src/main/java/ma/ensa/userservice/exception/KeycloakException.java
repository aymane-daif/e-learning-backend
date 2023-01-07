package ma.ensa.userservice.exception;

public class KeycloakException extends  Exception{
    public static final String MESSAGE = "For some reason our authentication serviceclient is not working properly, please try later";

    public KeycloakException(String msg){
        super(msg);
    }

    public KeycloakException(){
        super(MESSAGE);
    }

}
