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
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="user_credit")

public class UserCreditEntity extends BaseEntityAudit implements Serializable {


    @Column(name = "user_id")
    private Long userId;

    @Column(name="credit_limit")
    private Long creditLimit;

    @Column(name="credit_status")
    private String creditStatus;


}