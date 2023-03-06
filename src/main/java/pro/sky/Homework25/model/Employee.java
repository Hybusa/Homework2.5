package pro.sky.Homework25.model;

import java.util.Objects;

public class Employee {

    private final String firstName;

    private final String lastName;
    private final int employeeHash;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeHash = Objects.hash( firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s",
                this.lastName,
                this.firstName);
    }

    @Override
    public int hashCode() {
        return employeeHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        return this.firstName.equals(((Employee) obj).firstName) &&
                this.lastName.equals(((Employee) obj).lastName);
    }
}