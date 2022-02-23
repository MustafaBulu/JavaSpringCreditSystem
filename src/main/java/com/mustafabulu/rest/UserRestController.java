package com.mustafabulu.rest;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mustafabulu.dto.UserDto;
import com.mustafabulu.entity.UserEntity;
import com.mustafabulu.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserRestController {


    @Autowired
    public UserServices userServices;

    // ROOT
    // http://localhost:8080/api/v1/index
    @GetMapping("/index")
    @ResponseBody
    public String  getRoot() {
        return "index Sayfası";
    }

    //LIST
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<UserDto> userDto = (List<UserDto>) userServices.getAllUsers();
        return userDto;
    }


    //FIND with tc
    // http://localhost:8080/api/v1/users/tc/1
    @GetMapping("/users/tc/{identificationNumber}")
    public ResponseEntity<UserDto> getUserByIdentificationNumber(@PathVariable Long identificationNumber) {
        ResponseEntity<UserDto> userDto =userServices.getUserByIdentificationNumber(identificationNumber);
        return userDto;


        /*public ResponseEntity<ResponseEntity<UserDto>> getUserById(@PathVariable Long id) {
            ResponseEntity<UserDto> userDto =userServices.getAllUsers(id);
            return ResponseEntity.ok(userDto);*/
    }

    //get status
    // http://localhost:8080/api/v1/users/getstatus/12345
    @GetMapping("/users/getstatus/{identificationNumber}")
    public ResponseEntity<UserDto> getUserByCreditStatus(@PathVariable Long identificationNumber) {
        ResponseEntity<UserDto> userDto =userServices.getUserByIdentificationNumber(identificationNumber);
        return userDto;
    }

    //SAVE
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        userServices.createUser(userDto);
        return userDto;
    }

    //UPDATE
    // http://localhost:8080/api/v1/users/2
    @PutMapping("/users/{identificationNumber}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long identificationNumber, @RequestBody UserDto userDetails) {
        userServices.updateUser(identificationNumber, userDetails);
        return ResponseEntity.ok(userDetails);
    }

    //DELETE
    // http://localhost:8080/api/v1/users/7
    @DeleteMapping("/users/{identificationNumber}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long identificationNumber) {
        userServices.deleteUser(identificationNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}