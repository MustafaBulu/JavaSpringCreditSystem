package com.mustafabulu.services.impl;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserCreditEntity;
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
    public ResponseEntity<UserDto> getUserByCreditStatus(Long identificationNumber, UserDto userDto) {
        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));

        int credit_score=creditService.getCreditScore(identificationNumber);

        int credit_limit_multiplier=4;
        if (credit_score < 500) {
            user.getUserCredit().setCreditStatus("RED");
        } else if (credit_score >= 500 && credit_score < 1000 && user.getMonthlyIncome() < 5000) {
            user.getUserCredit().setCreditStatus("ONAY");
            user.getUserCredit().setCreditLimit(10000L);
        } else if (credit_score >= 500 && credit_score < 1000 && user.getMonthlyIncome() >= 5000) {
            user.getUserCredit().setCreditStatus("ONAY");
            user.getUserCredit().setCreditLimit(20000L);
        } else if (credit_score >= 1000) {
            user.getUserCredit().setCreditStatus("ONAY");
            user.getUserCredit().setCreditLimit(20000L);
        }
        userRepository.save(user);
        return ResponseEntity.ok(userDto);
    }

    @Override
    public void createUser(UserDto userDto) {
        UserEntity user = DtoToEntity(userDto); //ModelMapper
        UserCreditEntity userCredit =CreditDtoToEntity(userDto); //ModelMapper

        userCredit.setUserId(user.getIdentificationNumber());
        int credit_score=creditService.getCreditScore(user.getIdentificationNumber());

        int credit_limit_multiplier=4;
        if (credit_score < 500) {
            userCredit.setCreditStatus("RED");
        } else if (credit_score >= 500 && credit_score < 1000 && user.getMonthlyIncome() < 5000) {
            userCredit.setCreditStatus("ONAY");
            userCredit.setCreditLimit(10000L);
        } else if (credit_score >= 500 && credit_score < 1000 && user.getMonthlyIncome() >= 5000) {
            userCredit.setCreditStatus("ONAY");
            userCredit.setCreditLimit(20000L);
        } else if (credit_score >= 1000) {
            userCredit.setCreditStatus("ONAY");
            userCredit.setCreditLimit(20000L*credit_limit_multiplier);
        }

        user.setUserCredit(userCredit);
        userRepository.save(user);
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
    public ResponseEntity<UserDto> updateUser(Long identificationNumber, UserDto userDetails){
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
    //Model Mapper EntityToDto
    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    //Model Mapper DtoToEntity
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    //Model Mapper EntityToDto
    @Override
    public UserCreditEntity CreditDtoToEntity(UserDto userDto) {
        UserCreditEntity userCreditEntity = modelMapper.map(userDto, UserCreditEntity.class);
        return userCreditEntity;
    }


}