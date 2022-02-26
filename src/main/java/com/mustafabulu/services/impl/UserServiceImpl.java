package com.mustafabulu.services.impl;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;
import com.mustafabulu.exception.ResourceNotFoundException;
import com.mustafabulu.repository.UserRepository;
import com.mustafabulu.services.CreditService;
import com.mustafabulu.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CreditService creditService;



    @Override
    public List<UserDto> getAllUsers(){
        List<UserDto> listDto = new ArrayList<>();
        Iterable<UserEntity> userList = userRepository.findAll();
        for (UserEntity entity : userList) {
            UserDto userDto = EntityToDto(entity);//model
            listDto.add(userDto);
        }
        return  listDto;
    }

    @Override
    public ResponseEntity<UserDto> getUserByIdentificationNumber(Long identificationNumber) {
        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));
        UserDto userDto = EntityToDto(user);//model
        return ResponseEntity.ok(userDto);
    }




    @Override
    public ResponseEntity<UserDto> getUserByCreditStatus(@PathVariable Long identificationNumber, UserDto userDto) {
        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));

        int credit_score=creditService.getCreditScore(identificationNumber);

        int credit_limit_multiplier=4;
        if (credit_score<500){
            user.setCreditStatus("RED");
        }else if(credit_score>= 500 && credit_score<1000 && user.getMonthlyIncome()<5000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(10000L);
            userRepository.save(user);
        }else if(credit_score>= 500 && credit_score<1000 && user.getMonthlyIncome()>=5000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(20000L);
        }else if(credit_score>=1000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(user.getMonthlyIncome()*credit_limit_multiplier);
        }
        userRepository.save(user);
        return ResponseEntity.ok(userDto);
    }


    @Override
    public UserDto createUser(UserDto userDto) { //@RequestBody
        UserEntity user = DtoToEntity(userDto);//ModelMapper
        int credit_score=creditService.getCreditScore(user.getIdentificationNumber());

        int credit_limit_multiplier=4;
        if (credit_score<500){
            user.setCreditStatus("RED");
        }else if(credit_score>= 500 && credit_score<1000 && user.getMonthlyIncome()<5000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(10000L);
            userRepository.save(user);
        }else if(credit_score>= 500 && credit_score<1000 && user.getMonthlyIncome()>=5000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(20000L);
        }else if(credit_score>=1000){
            user.setCreditStatus("ONAY");
            user.setCreditLimit(user.getMonthlyIncome()*credit_limit_multiplier);
        }

        userRepository.save(user);
        return userDto;
    }



    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long identificationNumber){
        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable Long identificationNumber, @RequestBody UserDto userDetails){
        UserEntity userEntity = DtoToEntity(userDetails);//ModelMapper

        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));

        user.setIdentificationNumber( userEntity.getIdentificationNumber());
        user.setFirstName_lastName(userEntity.getFirstName_lastName());
        user.setMonthlyIncome(userEntity.getMonthlyIncome());
        user.setPhoneNumber(userEntity.getPhoneNumber());

        UserEntity updatedUser = userRepository.save(user);
        UserDto teacherDto = EntityToDto(updatedUser);//model
        return ResponseEntity.ok(teacherDto);
    }



    ////////////////////////////////////
    //Model Mapper Entity ==> Dto
    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    //Model Mapper Dto  ==> Entity
    //Model Mapper Dto  ==> Entity
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }
}