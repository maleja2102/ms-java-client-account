package com.devsu.ms_java_client.domain.model;

import com.devsu.ms_java_client.domain.model.enums.Gender;
import java.util.Objects;

public record Identity(
        String name,
        Gender gender,
        Integer age,
        String identificationNumber,
        String typeId) {

    public Identity {
        Objects.requireNonNull(name, "Name is required");
        Objects.requireNonNull(gender, "Gender is required");
        Objects.requireNonNull(age, "Age is required");
        Objects.requireNonNull(identificationNumber, "Identification number is required");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        if (age < 0) {
            throw new IllegalArgumentException("Age must be positive");
        }

        if (identificationNumber.isBlank()) {
            throw new IllegalArgumentException("Identification number must not be blank");
        }
    }
}
