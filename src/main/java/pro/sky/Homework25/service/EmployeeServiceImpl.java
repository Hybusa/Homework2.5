package pro.sky.Homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.emplyee.Employee;
import pro.sky.Homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exception.EmployeeNotFoundException;
import pro.sky.Homework25.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>(10);

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeList.size() > 9)
            throw new EmployeeStorageIsFullException("Employee Book is full.");

        if (checkParametersAndFindEmployee(firstName, lastName) != null) {
            throw new EmployeeAlreadyAddedException("Employee is already in the Book.");
        }

        final Employee tmpEmployee = new Employee(firstName, lastName);
        employeeList.add(tmpEmployee);
        System.out.println("Employee added\n");
        return tmpEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employeeList.size() < 1)
            throw new EmployeeNotFoundException("The book is empty");

        final Employee tmpEmployee = checkParametersAndFindEmployee(firstName, lastName);

        if (tmpEmployee != null)
            employeeList.remove(tmpEmployee);

        System.out.println("Employee removed\n");

        return tmpEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        final Employee tmpEmployee = checkParametersAndFindEmployee(firstName, lastName);
        if (tmpEmployee != null) {
            System.out.println("employee found");
            return tmpEmployee;
        }

        throw new EmployeeNotFoundException("There is no such employee");
    }

    private Employee findEmployeeByName(String firstName, String lastName) {

        for (Employee obj : employeeList) {
            if (obj.getFirstName().equals(firstName) && obj.getLastName().equals(lastName))
                return obj;
        }

        return null;
    }

    private Employee checkParametersAndFindEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new InputMismatchException("Parameter is null");

        return findEmployeeByName(firstName, lastName);
    }
}
