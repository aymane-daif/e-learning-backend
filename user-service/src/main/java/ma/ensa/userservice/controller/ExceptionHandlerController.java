package ma.ensa.userservice.controller;


import ma.ensa.userservice.exception.EmailAlreadyUsed;
import ma.ensa.userservice.exception.KeycloakException;
import ma.ensa.userservice.exception.NickNameALreadyUsed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NickNameALreadyUsed.class)
    public ResponseEntity<String> nickNameAlreadyUsed(Exception exp, WebRequest request){
            return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyUsed.class)
    public ResponseEntity<String> emailAlreadyUsed(Exception exp, WebRequest request){
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KeycloakException.class)
    public ResponseEntity<String> keycloakProblem(Exception exp, WebRequest request){
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
