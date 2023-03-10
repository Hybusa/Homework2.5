package pro.sky.Homework25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    @ResponseStatus(HttpStatus.OK)
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName
    ) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "remove")
    @ResponseStatus(HttpStatus.OK)
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName
    ) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "find")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName
    ) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "getAll")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
    }




    @ExceptionHandler({
            EmployeeAlreadyAddedException.class,
            EmployeeNotFoundException.class,
            EmployeeStorageIsFullException.class,
    })
    public ResponseEntity<String> handleException(RuntimeException e){
        HttpStatus status;
        switch(e.getClass().getName()){
            case "EmployeeAlreadyAddedException":
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            case "EmployeeNotFoundException":
                status = HttpStatus.NO_CONTENT;
                break;
            case "EmployeeStorageIsFullException":
                status = HttpStatus.INSUFFICIENT_STORAGE;
                break;
            default:
                status = HttpStatus.BAD_GATEWAY;
        }

        return ResponseEntity
                .status(status)
                .body(e.getMessage());
    }
}
