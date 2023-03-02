package pro.sky.Homework25.Service;

public class EmployeeStorageIsFullException extends RuntimeException{
    EmployeeStorageIsFullException(String message){
        super(message);
    }
}
