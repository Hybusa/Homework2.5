package pro.sky.Homework25.Service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.InputMismatchException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private int counter = 0;
    Employee[] employees = new Employee[0];

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (firstName == null || lastName == null)
            throw new InputMismatchException("Wrong input");
        if (counter > 9)
            throw new EmployeeStorageIsFullException("Employee Book is full.");

        Employee tmpEmployee = new Employee(firstName, lastName);
        if (findEmployeeByName(firstName, lastName) != null) {
            throw new EmployeeAlreadyAddedException("Employee is already in the Book.");
        }

        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = tmpEmployee;
        System.out.println("Employee added\n");
        counter++;
        return tmpEmployee;

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employees.length - 1 < 0) {
            throw new EmployeeNotFoundException("The book is empty");
        }
        Employee[] newEmployees = new Employee[employees.length - 1];
        if (findEmployeeByName(firstName, lastName) != null) {

            for (int i = 0, k = 0; i < employees.length; i++) {
                if (!(employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastName))) {
                    newEmployees[k] = employees[i];
                    k++;
                }
            }
        }
        employees = newEmployees;
        System.out.println("Employee removed\n");
        counter--;

        return new Employee(firstName, lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        Employee tmpEmployee = findEmployeeByName(firstName, lastName);
        if (tmpEmployee != null)
            return tmpEmployee;

        throw new EmployeeNotFoundException("There is no such employee");
    }

    private Employee findEmployeeByName(String firstName, String lastName) {

        for (Employee obj : employees) {
            if (obj.getFirstName().equals(firstName) && obj.getLastName().equals(lastName))
                return obj;
        }

        return null;
    }
}
