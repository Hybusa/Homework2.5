package pro.sky.Homework25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.Homework25.emplyee.Employee;
import pro.sky.Homework25.service.EmployeeServiceImpl;


@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName
    ) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "remove")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName
    ) {
        return employeeService.removeEmployee(firstName, lastName);
    }


    @GetMapping(path = "find")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName
    ) {
        return employeeService.findEmployee(firstName, lastName);
    }
}
