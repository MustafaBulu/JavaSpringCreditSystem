package com.mustafabulu.services;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserCreditEntity;
import com.mustafabulu.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface UserServices {

    //CRUD
    public List<UserDto> getAllUsers();
    public void createUser(UserDto userDto);
    public ResponseEntity<UserDto> getUserByIdentificationNumber(Long identificationNumber);
    public ResponseEntity<UserDto> getUserByCreditStatus(Long identificationNumber,UserDto userDto);
    public ResponseEntity<UserDto> updateUser(Long identificationNumber, UserDto userDto);
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long identificationNumber);

    //model mapper
    public UserDto EntityToDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);

    public UserCreditEntity CreditDtoToEntity(UserDto userDto);



}
