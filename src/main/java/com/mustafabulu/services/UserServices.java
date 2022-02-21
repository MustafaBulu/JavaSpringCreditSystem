package com.mustafabulu.services;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserServices {

    //CRUD
    public List<UserDto> getAllUsers();
    public UserDto createUser(UserDto userDto);
    public ResponseEntity<UserDto> getUserById(Long id);
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto);
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id);

    //model mapper
    public UserDto EntityToDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);


}
