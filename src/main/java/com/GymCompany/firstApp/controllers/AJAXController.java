package com.GymCompany.firstApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GymCompany.firstApp.model.TempUserDTO;
import com.GymCompany.firstApp.model.TokenDTO;
import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;
import com.GymCompany.jwtConfig.JwtFilter;
import com.GymCompany.jwtConfig.TokenProvider;

@RestController
public class AJAXController {
	@Autowired
	private UserListService userListService;
	
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	public AJAXController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
		super();
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@PostMapping("/rddCheck")
	public int rddUserId(@RequestBody String userIdData) { 
		System.out.println("Data received from client: " + userIdData);
		int result=0;
		try {
			
			result=userListService.redundancyCheck(userIdData);
			System.out.println("rddResult:"+result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@PostMapping("/registerCheck")
    public int registerUser(@RequestBody UserListDTO userDTO) {
		int result=0;
        try {
            
            userListService.registerUser(userDTO);
            result=1;
            return result;
        } catch (IllegalArgumentException e) {
            result=0;
            return result ;
        }
    }
	
	
	
	  @PostMapping("/loginCheck")
	    public ResponseEntity<TokenDTO> authorize(@Valid @RequestBody TempUserDTO tempUserDTO) {  // 
	    	
	    		  UsernamePasswordAuthenticationToken authenticationToken =
	  	                new UsernamePasswordAuthenticationToken(tempUserDTO.getUserId(), tempUserDTO.getUserPw()); //입력내용받아 토큰생성
	  	        
	  	        System.out.println("authenticationToken:"+authenticationToken); //not validated yet
	  	       
	  	        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);   // validation happens here
	  	        //only on Successful validation of right clause returns authentication object if not the obj is not made only exception will be thrown
	  	       // maybe implementing  AuthenticateManager and using it to set roles
	  	        //authenticationManager validates incoming object using UserDetailsService 
	  	        
	  	        
	  	        
	  	        //and on  successful authentication(validation) passes authentication object
	  	        SecurityContextHolder.getContext().setAuthentication(authentication); //establishing SecurityContext.    //bm
	  	        
	  	        
	  	        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
		        String jwt = tokenProvider.createToken(authentication); 
		        
		        
		        System.out.println("jwt(login):"+jwt); // printed out cuz it's validated 
		        
		        
		        HttpHeaders httpHeaders = new HttpHeaders();
		        
		        // response header에 jwt token에 넣어줌
		        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		        
		        
//		        System.out.println("Successfully authenticated. Security context contains: " +
//		        		 SecurityContextHolder.getContext().getAuthentication());
		
	        
	        
	        // tokenDto를 이용해 response body에도 넣어서 리턴
	        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
	    }
	    
	
	
	   

	    
}
	
