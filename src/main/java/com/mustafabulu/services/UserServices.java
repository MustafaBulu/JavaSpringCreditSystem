package com.mustafabulu.services;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;

public interface UserServices {

    //CRUD
    public void save(UserDto userDto);
    public void delete(Long userId);
    public UserDto find(Long userId);
    public Iterable<UserDto> getAllUser();

    //model mapper
    public UserDto EntityToDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);


}