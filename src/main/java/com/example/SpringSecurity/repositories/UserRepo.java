package com.example.SpringSecurity.repositories;

import com.example.SpringSecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
