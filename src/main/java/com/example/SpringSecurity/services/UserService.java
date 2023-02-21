package com.example.SpringSecurity.services;

import com.example.SpringSecurity.payloads.UserDto;

import java.util.List;

public interface UserService
{
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer Userid);
    List<UserDto> getAllUsers();
    void deleteUser(Integer Userid);
}
