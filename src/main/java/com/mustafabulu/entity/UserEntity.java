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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="user")

public class UserEntity extends BaseEntityAudit implements Serializable {

    @Column(name="identification_number")
    private String identificationNumber;

    @Column(name="firstname_lastname")
    private String firstName_lastName;

    @Column(name="monthly_income")
    private String monthlyIncome;

    @Column(name="phone_number")
    private String phoneNumber;




}
