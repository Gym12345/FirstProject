package com.GymCompany.firstApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GymCompany.firstApp.service.UserListService;

@RestController
public class AJAXController {
	@Autowired
	private UserListService userListService;
	
	@PostMapping("/rddCheck")
	public int rddUserId(@RequestBody String userIdData) { 
		System.out.println("Data received from client: " + userIdData);
		int result=0;
		try {
			
			result=userListService.redundancyCheck(userIdData);
			System.out.println("rddResult:"+result);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
}	
