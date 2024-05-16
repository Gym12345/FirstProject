package com.GymCompany.firstApp.service;

import com.GymCompany.firstApp.model.UserListDTO;

public interface UserListService {
    void registerUser(UserListDTO userDTO);
    int redundancyCheck(String userId);
}
