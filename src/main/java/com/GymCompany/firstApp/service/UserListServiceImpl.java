package com.GymCompany.firstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.repository.UserListRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListRepository userListRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private void authenticateUser(UserListDTO userDTO) {
//        // Create the authentication object
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDTO.getUserId(), userDTO.getUserPw());
//
//        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//
//        // Set the authentication into the SecurityContextHolder
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
    
    @Override
    @Transactional
    public void registerUser(UserListDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
 
        UserListDTO newUser = new UserListDTO();
        newUser.setUserId(userDTO.getUserId());
        newUser.setUserPw(encodedPassword);
        newUser.setUserName(userDTO.getUserName());
        newUser.setJoinDate(LocalDate.now());
        newUser.setLastLoginTime(LocalDateTime.now());

        userListRepository.save(newUser);  // save is defined in JpaRepository interface
       // authenticateUser(userDTO); // error occurs here
    }
   
    @Override
    @Transactional(readOnly = true)
    public int redundancyCheck(String userId) {
        List<UserListDTO> userList = userListRepository.findAll();
        
        userId = userId.replaceAll("^\"|\"$", "");  // Remove quotes from userId if present
        
        int result = 0;
        for (UserListDTO user : userList) {
            if (user.getUserId().equals(userId)) {
                result = 1;
                return result;
            }
        }
        
        return result;
    }
}
