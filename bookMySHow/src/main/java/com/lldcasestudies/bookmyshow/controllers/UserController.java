package com.lldcasestudies.bookmyshow.controllers;

import com.lldcasestudies.bookmyshow.dtos.ResponseStatus;
import com.lldcasestudies.bookmyshow.dtos.SignUpRequestDTO;
import com.lldcasestudies.bookmyshow.dtos.SignUpResponseDTO;
import com.lldcasestudies.bookmyshow.models.User;
import com.lldcasestudies.bookmyshow.services.UserService;

public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpResponseDTO signUpResponseDTO=new SignUpResponseDTO();
        try {
            User user=userService.login(signUpRequestDTO.getEmail(),signUpRequestDTO.getPassword());
            signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponseDTO.setUserId(user.getId());
        }
        catch (Exception exception){
            signUpResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return signUpResponseDTO;
    }
}
