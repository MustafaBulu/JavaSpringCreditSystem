package com.mustafabulu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDto {
    private Long id;
    private String identificationNumber;
    private String firstName_lastName;
    private String monthlyIncome;
    private String phoneNumber;
    private String creditLimit;
    private String creditStatus;



}
