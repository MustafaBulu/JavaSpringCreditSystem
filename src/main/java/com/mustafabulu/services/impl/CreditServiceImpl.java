package com.mustafabulu.services.impl;

import com.mustafabulu.services.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {

    //kredi skorunu getiren servis daha önceden yazıldığı varsayıldığı için bu kısımda el ile kontrol edilmesi için bu şekilde oluşturulmuştur.

    @Override
    public int getCreditScore(long identificationNumber) {
        return 1111;
    }
}
