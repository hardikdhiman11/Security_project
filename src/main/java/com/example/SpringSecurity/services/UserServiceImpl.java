package com.example.SpringSecurity.services;

import com.example.SpringSecurity.entities.User;
import com.example.SpringSecurity.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity.payloads.UserDto;
import com.example.SpringSecurity.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    //Constructor dependency injection
    private final UserRepo userRepo;    //Interface reference

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return usertoDto(savedUser);    // Here we are returning a userDto type by converting it from user
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId)
    {


        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));      // Finding if user exists or not
        user.setName(userDto.getName());        //updating if user exists
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);
        return usertoDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return usertoDto(user);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<User> users= userRepo.findAll();   // List of users in form of User
        // Convert list of User to UserDto -> can be done using map
        List<UserDto> usersDto = users.stream().map(user -> usertoDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public void deleteUser(Integer userId)
    {
        userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        userRepo.deleteById(userId);
    }


    public User dtoToUser(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setAbout(user.getAbout());
        return user;
    }

    public UserDto usertoDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
