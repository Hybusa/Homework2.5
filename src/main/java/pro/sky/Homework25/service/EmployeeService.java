package pro.sky.Homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;
import java.util.Collection;
import java.util.Optional;

@Service
public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Optional<Employee> getMaxSalaryFromDepartment(Optional<Department> department);
    Optional<Employee> getMinSalaryFromDepartment(Optional<Department> department);
    Collection<Employee> getAllEmployeesFromDepartment(Optional<Department> department);
    Collection<Collection<Employee>> getAllSortedByDepartments();

    Collection<Employee> getAllEmployees();

}
