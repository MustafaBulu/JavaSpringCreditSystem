package com.mustafabulu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="users")

public class UserEntity extends BaseEntityAudit implements Serializable {

    @Column(name="identification_number")
    private Long identificationNumber;

    @Column(name="firstname_lastname")
    private String firstName_lastName;

    @Column(name="monthly_income")
    private String monthlyIncome;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="credit_limit")
    private String creditLimit;

    @Column(name="credit_status")
    private String creditStatus;


    public UserEntity(Long identificationNumber, String firstName_lastName, String monthlyIncome, String phoneNumber, String creditLimit, String creditStatus) {
        this.identificationNumber = identificationNumber;
        this.firstName_lastName = firstName_lastName;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.creditStatus = creditStatus;
    }
}