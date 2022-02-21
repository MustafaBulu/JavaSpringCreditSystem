package com.mustafabulu.services.impl;

import com.mustafabulu.bean.ModelMapperBean;
import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;
import com.mustafabulu.repository.UserRepository;
import com.mustafabulu.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperBean modelMapperBean;


    //SAVE
    @Override
    public void save(@RequestBody UserDto userDto) {
        UserEntity userEntity=DtoToEntity(userDto);
        userRepository.save(userEntity);
    }

    //DELETE
    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    //FIND
    @Override
    public UserDto find(Long userId) {

        UserDto userDto=new UserDto();
        Optional<UserEntity>  optional=  userRepository.findById(userId);

        if(optional.isPresent()){
            UserEntity userEntity=optional.get();
            userDto=EntityToDto(userEntity);//model mapper
        }else{
            userDto=UserDto
                    .builder()
                    .id(0L)
                    .firstName_lastName("mustafa bulu")
                    .identificationNumber("12125215123")
                    .monthlyIncome("5000")
                    .phoneNumber("5540000000")
                    .build();
        }
        return userDto;
    }

    //LIST
    @Override
    public List<UserDto> getAllUser() {
        List<UserDto>  listDto=new ArrayList<>();
        Iterable<UserEntity> userEntities=userRepository.findAll();
        for(UserEntity temp :userEntities ){
            UserDto userDto=EntityToDto(temp);
            listDto.add(userDto);

        }
        return listDto;
    }

    //model Mapper
    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        return null;
    }
}