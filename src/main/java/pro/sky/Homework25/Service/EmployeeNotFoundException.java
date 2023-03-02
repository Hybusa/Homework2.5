package pro.sky.Homework25.Service;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(String message){
        super(message);
    }
}
