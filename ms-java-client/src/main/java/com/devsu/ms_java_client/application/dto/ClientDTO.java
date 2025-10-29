package com.devsu.ms_java_client.application.dto;

import com.devsu.ms_java_client.domain.model.enums.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long clientId;
    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Name must have at most 150 characters")
    private String name;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be positive")
    private Integer age;

    @NotBlank(message = "Identification number is required")
    private String identificationNumber;

    private String typeId;
    private String address;
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Status is required")
    private Boolean status;
}
