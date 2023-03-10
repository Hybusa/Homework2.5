package pro.sky.Homework25.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoSuchDepartmentException extends RuntimeException{
    public NoSuchDepartmentException(String message) {
        super(message);
    }
}
