package pro.sky.Homework25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Homework25.exception.NoSuchDepartmentException;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.service.DepartmentServiceImpl;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;


    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String start(){
        return "Departments";
    }

    @GetMapping(path = "max-salary")
    @ResponseStatus(HttpStatus.OK)
    public Employee getMaxSalary(@RequestParam("departmentId") int departmentId){
        Optional<Employee> tmpEmployee = departmentService
                .getMaxSalaryFromDepartment(Department.valueOf(departmentId));
        if(tmpEmployee.isEmpty())
            throw new NoSuchDepartmentException("There is no employee in that department");
        System.out.println("max salary");
        return tmpEmployee.get();
    }

    @GetMapping(path = "min-salary")
    @ResponseStatus(HttpStatus.OK)
    public Employee getMinSalary(@RequestParam ("departmentId") int departmentId){
        System.out.println("Min saalry");
        return departmentService
                .getMinSalaryFromDepartment(Department.valueOf(departmentId));
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Employee> getAllFromDepartment(
            @RequestParam (value = "departmentId",required = false)
            Integer departmentId){
        if(departmentId != null)
            return (departmentService.getAllEmployeesFromDepartment(Department.valueOf(departmentId)));

        return departmentService.getAllSortedByDepartments();
    }

    @ExceptionHandler({
            NoSuchDepartmentException.class
    })
    public ResponseEntity<String> handleException(NoSuchDepartmentException e) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(e.getMessage());

    }

}
