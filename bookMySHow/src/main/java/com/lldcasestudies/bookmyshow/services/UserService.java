package com.lldcasestudies.bookmyshow.services;

import com.lldcasestudies.bookmyshow.exceptions.InvalidUserException;
import com.lldcasestudies.bookmyshow.models.User;
import com.lldcasestudies.bookmyshow.repositories.UserRepository;

import java.util.Optional;

public class UserService {
    UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User login(String email, String password){
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            System.out.println("User not found. Please signup.");
            return signUp(email,password);
        }
        User user=optionalUser.get();
        String userPassword=user.getPassword();
        // B-crypt Algorithm
        if(password.equals(userPassword)){
            return user;
        }
        else{
            throw new RuntimeException("Wrong password. Please try again.");
        }
    }
    public  User signUp(String email,String password){
        // Return a new User in the DB
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
