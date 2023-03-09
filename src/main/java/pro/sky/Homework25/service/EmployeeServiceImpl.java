package pro.sky.Homework25.service;

import org.springframework.stereotype.Component;
import pro.sky.Homework25.exception.NoSuchDepartmentException;
import pro.sky.Homework25.model.Department;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    private final int BOOK_MAX_SIZE = 10;
    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeMap.size() > BOOK_MAX_SIZE)
            throw new EmployeeStorageIsFullException("Employee Book is full.");

        if (employeeMap.put(Objects.hash(firstName, lastName), new Employee(firstName, lastName)) != null) {
            throw new EmployeeAlreadyAddedException("Employee is already in the Book.");
        }

        System.out.println("Employee added\n");

        return employeeMap.get(Objects.hash(firstName, lastName));
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employeeMap.size() < 1)
            throw new EmployeeNotFoundException("The book is empty");

        if (!employeeMap.containsKey(Objects.hash(firstName, lastName)))
            throw new EmployeeNotFoundException("No such employee in the book");

        System.out.println("Employee removed\n");

        return employeeMap.remove(Objects.hash(firstName, lastName));
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        if (!employeeMap.containsKey(Objects.hash(firstName, lastName)))
            throw new EmployeeNotFoundException("There is no such employee");

        return employeeMap.get(Objects.hash(firstName, lastName));
    }

    @Override
    public Optional<Employee> getMaxSalaryFromDepartment(Optional<Department> department) {

        return getDepartmentEmplyeesStream(department)
                .max(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Optional<Employee> getMinSalaryFromDepartment(Optional<Department> department) {

        return getDepartmentEmplyeesStream(department)
                .min(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Collection<Employee> getAllEmployeesFromDepartment(Optional<Department> department) {
        return getDepartmentEmplyeesStream(department).collect(Collectors.toList());
    }

    @Override
    public Collection<Collection<Employee>> getAllSortedByDepartments() {
        Collection<Collection<Employee>> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            result.add(getDepartmentEmplyeesStream(Department.valueOf(i)).collect(Collectors.toList()));
        }
        return result;
    }

    private Stream<Employee> getDepartmentEmplyeesStream(Optional<Department> department) {
        if (department.isEmpty()) {
            throw new NoSuchDepartmentException("There is no such department");
        }
        return employeeMap.values().stream().filter(employee -> employee.getDepartment() == department.get());
    }


    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeMap.values();
    }
}
