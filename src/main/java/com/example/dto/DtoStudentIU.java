package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

    @NotEmpty(message = "firstname boş olmaz")
    @Max(value = 20, message = "max 20 karakter ")
    @Min(value = 3, message = "min 2 karakter")
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthOfDate;
    @Size(min = 11, max = 11, message = "tckn 11 karaketer olmalı")
    private String tckn;
    @Email(message = "email formatında olmalı")
    private String email;

}
