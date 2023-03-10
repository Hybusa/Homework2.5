package pro.sky.Homework25.model;

import java.util.Objects;
import java.util.Random;

public class Employee {

    private final String firstName;

    private final String lastName;

    private Department department;
    private double salary;

    public Employee(String firstName, String lastName) {
        Random rn = new Random();
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = Department.valueOf(rn.nextInt(7)).get();
        this.salary = rn.nextInt(500000 - 100000)+100000;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s",
                this.lastName,
                this.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        return this.firstName.equals(((Employee) obj).firstName) &&
                this.lastName.equals(((Employee) obj).lastName);
    }
}