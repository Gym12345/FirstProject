package com.GymCompany.firstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.repository.UserListRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserListService {

    @Autowired
    private UserListRepository userListRepository;
    
    
    @Transactional
    public void registerUser(UserListDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());

       
        UserListDTO newUser = new UserListDTO();
        newUser.setUserId(userDTO.getUserId());
        newUser.setUserPw(encodedPassword);
        newUser.setUserName(userDTO.getUserName());
        newUser.setJoinDate(LocalDate.now());
        newUser.setLastLoginTime(LocalDateTime.now());

      
        userListRepository.save(newUser);   //save is defined in JPARepository Interface  ,jpa used
    }
    
//    @Transactional(readOnly = true)
//    public List<UserListDTO> getAllUsers() {
//    	
//    	
//        return userListRepository.findAll();
//    }
    
    @Transactional(readOnly = true)
    public int redundancyCheck(String userId) {
      
        List<UserListDTO> userList = userListRepository.findAll();
        
        userId = userId.replaceAll("^\"|\"$", "");  // ajax 보내는 데이터에 쌍따옴표가 씌워진채로와서 없애줘야함 따옴표 <---2틀 잡아먹음 시간 ㅅㅂ
        
        System.out.println("userId"+userId);
        
        int result=0;
        for (int i=0; i<userList.size();i++) {
        	if(userList.get(i).getUserId().equals(userId) ) {
        		result=1;
        		System.out.println("result:"+result);
        		return result;
        	}
        	else {
        		continue;
        	}
        }
        
        
        System.out.println("result:"+result);
        return result;
    }
    
    
//    public boolean redundancyCheck(String userId) {
//        try {
//            UserListDTO user = userListRepository.findByUserId(userId);
//            return user != null;
//        } catch (EntityNotFoundException ex) {
//            // Log the exception or handle it as needed
//            ex.printStackTrace(); // Example: print the stack trace
//            return false; // Or handle the exception in another way
//        }
//    }
    
//    @Transactional
//    public void registerUser(UserListDTO userDTO) {
//        // Check if the user already exists
//
//        // Encrypt the password using BCrypt
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
//        userDTO.setUserPw(encodedPassword);
//
//        // Set other properties
//        userDTO.setJoinDate(LocalDate.now());
//        userDTO.setLastLoginTime(LocalDateTime.now());
//
//        // Insert the user using raw SQL query
//        userListRepository.insertUser(
//                userDTO.getUserId(),
//                userDTO.getUserPw(),
//                userDTO.getUserName(),
//                userDTO.getJoinDate(),
//                userDTO.getLastLoginTime()
//        );
//    }
    
//    @Transactional
//    public int redundancyCheck(String userId) {
//    	System.out.println(userId);
//    	 //UserListDTO d = new UserListDTO();
//    	// d.setUserId(userId);
//    	 
//    	 Map<String, Object> d = new HashMap<>();
//    	 d.put("userId", userId);
//    	 UserListDTO dto=userListRepository.findByUserId(d);
//         System.out.println("dtoUSerId:"+dto);
//         int result = 0;
//        return result;
//    }

   

}
