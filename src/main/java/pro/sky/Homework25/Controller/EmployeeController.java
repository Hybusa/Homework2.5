package pro.sky.Homework25.Controller;

import pro.sky.Homework25.Service.Employee;
import pro.sky.Homework25.Service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public String calculator(){
        return "Welcome to the EmployeeBook";
    }
    @GetMapping(path = "add")
    public Employee addEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ){
        return employeeService.addEmployee(firstName,lastName);
    }

    @GetMapping(path = "remove")
    public Employee removeEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ){
        return employeeService.removeEmployee(firstName,lastName);
    }


    @GetMapping(path = "find")
    public Employee findEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ){
        return employeeService.findEmployee(firstName,lastName);
    }
}
