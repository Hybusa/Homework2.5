package pro.sky.Homework25.model;

import java.util.Arrays;
import java.util.Optional;

public enum Department {
    DESIGN(0),
    PRODUCTION(1),
    RELEASE(2),
    MANAGEMENT(3),
    DEVELOPMENT(4),
    CONTENT(5);

    private final int number;

    Department(int number) {
        this.number = number;
    }

    public static Optional<Department> valueOf(int number) {
        return Arrays.stream(values())
                .filter(Department -> Department.number == number)
                .findFirst();
    }
}
