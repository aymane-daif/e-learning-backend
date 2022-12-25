package ma.ensa.mediaservice.controller;


import ma.ensa.mediaservice.exception.FileAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(FileAlreadyExists.class)
    public ResponseEntity<String> fileAlreadyExists(Exception exp, WebRequest request){
            return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
