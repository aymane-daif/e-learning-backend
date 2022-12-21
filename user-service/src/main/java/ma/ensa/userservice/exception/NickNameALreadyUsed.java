package ma.ensa.userservice.exception;

public class NickNameALreadyUsed extends Exception{

    public static final String MESSAGE = "This nickName is already used";

    public NickNameALreadyUsed(String msg){
        super(msg);
    }

    public NickNameALreadyUsed(){
        super(MESSAGE);
    }
}
