package com.GymCompany.firstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.repository.UserListRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserListService {

    @Autowired
    private UserListRepository userListRepository;
    
    
    
    
    
    @Transactional
    public void registerUser(UserListDTO userDTO) {
        // Check if the user already exists

        // Encrypt the password using BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
        userDTO.setUserPw(encodedPassword);

        // Set other properties
        userDTO.setJoinDate(LocalDate.now());
        userDTO.setLastLoginTime(LocalDateTime.now());

        // Insert the user using raw SQL query
        userListRepository.insertUser(
                userDTO.getUserId(),
                userDTO.getUserPw(),
                userDTO.getUserName(),
                userDTO.getJoinDate(),
                userDTO.getLastLoginTime()
        );
    }
    
    @Transactional
    public int redundancyCheck(String userId) {
    	System.out.println(userId);
        int result=userListRepository.userRddCheck(userId);
        return result;
    }


}
