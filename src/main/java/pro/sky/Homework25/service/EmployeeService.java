package pro.sky.Homework25.service;


import pro.sky.Homework25.model.Employee;

import java.util.Set;

interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Set<Employee> getAllEmployees();

}
