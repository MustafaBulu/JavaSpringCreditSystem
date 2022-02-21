package com.mustafabulu.rest;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mustafabulu.dto.UserDto;
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

    //FIND
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        List<UserDto> userDto = (List<UserDto>) userServices.getAllUsers();
        return ResponseEntity.ok(userDto.get(0));
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
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDetails) {
        userServices.updateUser(id, userDetails);
        return ResponseEntity.ok(userDetails);
    }

    //DELETE
    // http://localhost:8080/api/v1/users/7
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}