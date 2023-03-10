package pro.sky.Homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.model.Employee;

import java.util.Collection;

@Service
public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);



    Collection<Employee> getAllEmployees();

}
