package pro.sky.Homework25.service;

import org.springframework.stereotype.Component;
import pro.sky.Homework25.exception.NoSuchDepartmentException;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.model.EmployeeStorage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class DepartmentServiceImpl implements DepartmentService{

    private final Map<Integer, Employee> employeeMap = EmployeeStorage.getEmployeeMap();
    @Override
    public Optional<Employee> getMaxSalaryFromDepartment(Optional<Department> department) {

        return getDepartmentEmplyeesStream(department)
                .max(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Employee getMinSalaryFromDepartment(Optional<Department> department) {

        Optional<Employee> tmpEmployee = getDepartmentEmplyeesStream(department)
                .min(Comparator.comparing(Employee::getSalary));
        if(tmpEmployee.isEmpty())
            throw new NoSuchDepartmentException("There are no employees in this department");
        System.out.println("Min salary impl");
        return tmpEmployee.get();
    }

    @Override
    public Collection<Employee> getAllEmployeesFromDepartment(Optional<Department> department) {
        return getDepartmentEmplyeesStream(department).collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> getAllSortedByDepartments() {
        return employeeMap.values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    private Stream<Employee> getDepartmentEmplyeesStream(Optional<Department> department) {
        if (department.isEmpty()) {
            throw new NoSuchDepartmentException("There is no such department");
        }
        return employeeMap.values().stream().filter(employee -> employee.getDepartment() == department.get());
    }
}
