package pro.sky.Homework25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;
import pro.sky.Homework25.exception.NoSuchDepartmentException;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/add")
    @ResponseStatus(HttpStatus.OK)
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName
    ) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "/employee/remove")
    @ResponseStatus(HttpStatus.OK)
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName
    ) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/employee/find")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName
    ) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/employee/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/departments/max-salary")
    @ResponseStatus(HttpStatus.OK)
    public Employee getMaxSalary(@RequestParam ("departmentId") int departmentId){
        Optional<Employee> tmpEmployee = employeeService
                .getMaxSalaryFromDepartment(Department.valueOf(departmentId));
        if(tmpEmployee.isEmpty())
            throw new NoSuchDepartmentException("There is no employee in that department");
        return tmpEmployee.get();
    }

    @GetMapping(path = "/departments/min-salary")
    @ResponseStatus(HttpStatus.OK)
    public Employee getMinSalary(@RequestParam ("departmentId") int departmentId){
        Optional<Employee> tmpEmployee = employeeService
                .getMinSalaryFromDepartment(Department.valueOf(departmentId));
        if(tmpEmployee.isEmpty())
            throw new NoSuchDepartmentException("There is no employee in that department");
        return tmpEmployee.get();
    }

    @GetMapping("/departments/all")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Collection<Employee>> getAllFromDepartment(@RequestParam ("departmentId") Optional<Integer> departmentId){
        if(departmentId.isPresent())
            return List.of((employeeService.getAllEmployeesFromDepartment(Department.valueOf(departmentId.get()))));

        return employeeService.getAllSortedByDepartments();
    }


    @ExceptionHandler({
            EmployeeAlreadyAddedException.class,
            EmployeeNotFoundException.class,
            EmployeeStorageIsFullException.class,
            NoSuchDepartmentException.class
    })
    public ResponseEntity<String> handleException(RuntimeException e){
        HttpStatus status;
        switch(e.getClass().getName()){
            case "EmployeeAlreadyAddedException":
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            case "EmployeeNotFoundException":
            case "NoSuchDepartmentException":
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
