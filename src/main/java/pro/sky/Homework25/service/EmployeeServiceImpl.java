package pro.sky.Homework25.service;

import org.springframework.stereotype.Component;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.model.EmployeeStorage;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    private final int BOOK_MAX_SIZE = 10;
    private final Map<Integer, Employee> employeeMap = EmployeeStorage.getEmployeeMap();

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
    public Collection<Employee> getAllEmployees() {
        return employeeMap.values();
    }
}
