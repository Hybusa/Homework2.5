package pro.sky.Homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.model.Employee;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Set<Employee> employeeSet = new HashSet<>(10);

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeSet.size() > 9)
            throw new EmployeeStorageIsFullException("Employee Book is full.");

        final Employee tmpEmployee = new Employee(firstName, lastName);
        if (!employeeSet.add(tmpEmployee)) {
            throw new EmployeeAlreadyAddedException("Employee is already in the Book.");
        }

        System.out.println("Employee added\n");

        return tmpEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employeeSet.size() < 1)
            throw new EmployeeNotFoundException("The book is empty");

        final Employee tmpEmployee = new Employee(firstName, lastName);
        if (!employeeSet.remove(tmpEmployee))
            throw new EmployeeNotFoundException("No such employee in the book");

        System.out.println("Employee removed\n");

        return tmpEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {


        final Employee tmpEmployee = new Employee(firstName, lastName);
        for (Employee obj : employeeSet) {
            if (obj.equals(tmpEmployee))
                return obj;
        }

        throw new EmployeeNotFoundException("There is no such employee");
    }

}
