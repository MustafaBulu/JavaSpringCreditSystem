package com.mustafabulu.services.impl;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;
import com.mustafabulu.exception.ResourceNotFoundException;
import com.mustafabulu.repository.UserRepository;
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


    //LIST
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
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

    //FIND
    // http://localhost:8080/api/v1/users/2
    // get user by id rest api
    @GetMapping("/users/{id}")
    @Override
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        UserDto userDto = EntityToDto(user);//model
        return ResponseEntity.ok(userDto);
    }

    //FIND
    // http://localhost:8080/api/v1/users/tc/123123
    // get user by id rest api
    @GetMapping("/users/tc/{identificationNumber}")
    @Override
    public ResponseEntity<UserDto> getUserByIdentificationNumber(@PathVariable Long identificationNumber) {
        UserEntity user = userRepository.findUserEntitiesByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + identificationNumber));
        UserDto userDto = EntityToDto(user);//model
        return ResponseEntity.ok(userDto);
    }





    //SAVE
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    public UserDto createUser(@RequestBody  UserDto userDto) { //@RequestBody
        UserEntity userEntity = DtoToEntity(userDto);//ModelMapper
        userRepository.save(userEntity);
        return userDto;
    }

    //DELETE
    // http://localhost:8080/api/v1/users
    @DeleteMapping("/users/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //UPDATE
    // http://localhost:8080/api/v1/users
    @PutMapping("/users/{id}")
    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDetails){
        UserEntity userEntity = DtoToEntity(userDetails);//ModelMapper

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

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
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }
}