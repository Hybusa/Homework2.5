package pro.sky.Homework25.Service;

import java.util.Objects;

public class Employee {
    private final String firstName;

    private final String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        return Objects.hash(firstName, lastName);
    }


}