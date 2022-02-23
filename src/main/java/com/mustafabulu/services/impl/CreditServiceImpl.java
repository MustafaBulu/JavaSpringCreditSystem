package com.mustafabulu.services.impl;


import com.mustafabulu.services.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {

    @Override
    public int getCreditScore(long identificationNumber) {
        return 600;
    }
}
