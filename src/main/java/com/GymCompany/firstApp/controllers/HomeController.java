package com.GymCompany.firstApp.controllers;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
		System.out.println("registerMenu");

		return "registerMenu";
	}
	
	
	

	
	

	
}
