package com.example.SpringSecurity.controllers;

import com.example.SpringSecurity.payloads.UserDto;
import com.example.SpringSecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController
{
    // Constructor dependency injection
    private final UserService userService;  // Interface reference



    //Post -> For creating a user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto =userService.createUser(userDto);
        //return new ResponseEntity<>(createUserDto,HttpStatus.OK);
        return ResponseEntity.ok(createUserDto);
    }

    //Get   -> For getting a user by id
    @GetMapping("{id}")
     public ResponseEntity<UserDto> getUserById(@PathVariable("id") @RequestBody int userId)
     {
         UserDto getUserByIdDto = userService.getUserById(userId);
         //return new ResponseEntity<>(getUserByIdDto,HttpStatus.ACCEPTED);
         return ResponseEntity.ok(getUserByIdDto);
     }

    //GetAll  -> For getting all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> getAllDto = userService.getAllUsers();
        //return new ResponseEntity<>(getAllDto,HttpStatus.ACCEPTED);
        return ResponseEntity.ok(getAllDto);
    }


    //Put  -> For updating a user
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserById( @PathVariable("id") @RequestBody UserDto userDto ,int userId )
    {
        UserDto updateUserByIdDto = userService.updateUser(userDto,userId);
        //return new ResponseEntity<>(updateUserByIdDto,HttpStatus.ACCEPTED);
        return ResponseEntity.ok(updateUserByIdDto);
    }


    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") @RequestBody int userId)
    {
        userService.deleteUser(userId);
        //return new ResponseEntity<>("User has been deleted",HttpStatus.ACCEPTED);
        return ResponseEntity.ok("User has been successfully deleted");
    }


}
