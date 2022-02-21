package com.mustafabulu.rest;



import com.mustafabulu.dto.UserDto;
import com.mustafabulu.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000") //CORS
@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    public UserServices userServices;

    //LIST
    // http://localhost:8080/api/v1/users/list
    @GetMapping("/users/list")
    public List<UserDto> listUsers(){
        List<UserDto> userDto=userServices.getAllUser();
        return  userDto;
    }



    //FIND
    // http://localhost:8080/api/v1/users/find/1
    @GetMapping("/users/find/{id}")
    public UserDto findUsers(@PathVariable(value = "id") Long id){
        UserDto userDto=userServices.find(id);
        return  userDto;
    }


    //POST
    // http://localhost:8080/api/v1/users/post
    @PostMapping("/users/post")
    public UserDto postUsers(@RequestBody  UserDto userDto){
        userServices.save(userDto);
        return  userDto;
    }

    //PUT
    // http://localhost:8080/api/v1/users/put
    @PutMapping("/users/put")
    public void putUsers(UserDto userDto){
        userServices.save(userDto);
    }

    //DELETE
    // http://localhost:8080/api/v1/users/delete/1
    @DeleteMapping ("/users/delete/{id}")
    public void deleteUsers( @PathVariable(value = "id") Long id){
        userServices.delete(id);
    }


}
