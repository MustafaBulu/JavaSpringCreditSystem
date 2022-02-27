package com.mustafabulu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="users")

public class UserEntity extends BaseEntityAudit implements Serializable {

    @Column(name="identification_number")
    private Long identificationNumber;

    @Column(name="firstname_lastname")
    String firstName_lastName;

    @Column(name="monthly_income")
    Long monthlyIncome;

    @Column(name="phone_number")
    String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    UserCreditEntity userCredit;

    public UserEntity(Long identificationNumber, String firstName_lastName, Long monthlyIncome, String phoneNumber) {
        this.identificationNumber = identificationNumber;
        this.firstName_lastName = firstName_lastName;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
    }
}