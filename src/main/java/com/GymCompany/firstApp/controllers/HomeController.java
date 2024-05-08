package com.GymCompany.firstApp.controllers;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private UserListService userListService;
	
	
	@GetMapping(value = "/")
	public String goHome(HttpServletRequest request) {
		System.out.println("main");
		return "MainPage";
	}
	
	@GetMapping(value = "/loginMenu")
	public String loginMenu(HttpServletRequest request) {
		System.out.println("loginMenu");
		return "loginMenu";
	}
	
	
	
	@GetMapping(value = "/registerMenu")
	public String registerMenu(HttpServletRequest request) {
		return "registerMenu";
	}
	
	
	
	@PostMapping("/registerCheck")
    public String registerUser(
    		
            @RequestParam("userId") String userId,
            @RequestParam("userPw") String userPw,
            @RequestParam("userName") String userName,
            RedirectAttributes redirectAttributes) {
		System.out.println("registerChekc");
        // Create a new UserListDTO object with the form data
        UserListDTO userDTO = new UserListDTO();
        userDTO.setUserId(userId);
        userDTO.setUserPw(userPw);
        userDTO.setUserName(userName);
        userDTO.setJoinDate(LocalDate.now());
        System.out.println("userId:"+userDTO.getUserId());
        try {
            // Register the user
        	
        	userListService.registerUser(userDTO);
            // Redirect to a success page or show a success message
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
            return "loginMenu"; // Redirect to the login menu after successful registration
        } catch (IllegalArgumentException e) {
            // If user registration fails, redirect back to the registration form with an error message
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "registerMenu";
        }
    }
	
	
}
