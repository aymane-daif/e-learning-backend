package ma.ensa.userservice.exception;

import ma.ensa.userservice.entity.User;

public class UserDoesntExist extends Exception{

    private final static String MESSAGE = "This user doesn't exist";

    public UserDoesntExist(){
        super(MESSAGE);
    }
<<<<<<< HEAD
=======

>>>>>>> 70b230daae1752338880784a0ab3d468adace533
}
