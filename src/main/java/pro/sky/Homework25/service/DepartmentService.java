package pro.sky.Homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;

import java.util.Collection;
import java.util.Optional;

@Service
public interface DepartmentService {
    Optional<Employee> getMaxSalaryFromDepartment(Optional<Department> department);
    Employee getMinSalaryFromDepartment(Optional<Department> department);
    Collection<Employee> getAllEmployeesFromDepartment(Optional<Department> department);
    Collection<Employee> getAllSortedByDepartments();
}
