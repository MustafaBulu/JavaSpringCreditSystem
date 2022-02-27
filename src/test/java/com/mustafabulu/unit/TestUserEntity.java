package com.mustafabulu.unit;

import com.mustafabulu.entity.UserEntity;
import com.mustafabulu.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
public class TestUserEntity {

    @Autowired
    UserRepository userRepository;

    //CREATE
    @Test
    void createTest(){
        UserEntity userEntity=new UserEntity();
        userEntity.setIdentificationNumber(123124L);
        userEntity.setFirstName_lastName("Mustafa Bulu");
        userEntity.setMonthlyIncome(5000L);
        userEntity.setPhoneNumber("5549999900");
        userRepository.save(userEntity);
        long userId=userEntity.getId();
        Assertions.assertEquals(true,userRepository.findById(userId).isPresent());
    }

    //LIST
    @Test
    void listTest(){
        List<UserEntity> listem=userRepository.findAll();
        assertThat(listem).size().isGreaterThan(0);
    }

    //FIND
    @Test
    void findTest(){
        UserEntity find=userRepository.findById(1L).get();
        assertEquals("Mustafa Bulu",find.getFirstName_lastName());
    }

    //UPDATE
    @Test
    void updateTest(){
        UserEntity update=userRepository.findById(1L).get();
        update.setFirstName_lastName("değiştirdim");
        userRepository.save(update);
        assertNotEquals("eski yapı",update.getFirstName_lastName());
    }

    //DELETE
    @Test
    void deleteTest(){
        userRepository.deleteById(1L);
        assertThat(userRepository.existsById(1L)).isFalse();
    }

}