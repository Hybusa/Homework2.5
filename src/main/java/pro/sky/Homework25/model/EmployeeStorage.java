package pro.sky.Homework25.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeStorage {
    private static final Map<Integer, Employee> employeeMap = new HashMap<>();

    public static Map<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }
}
