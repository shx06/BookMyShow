package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.LoginUserResponseDTO;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.dtos.SignupUserRequestDTO;
import com.example.bookmyshow.dtos.SignupUserResponseDTO;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.serivces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupUserResponseDTO signUpUser(SignupUserRequestDTO req) {

        SignupUserResponseDTO response = new SignupUserResponseDTO();
        try {

            int userId = userService.signUpUser(req.getName(), req.getEmail(), req.getPassword());

            if (userId > 0) {
                response.setUserId(userId);
                response.setStatus(ResponseStatus.SUCCESS);
                response.setMessage("Sign up successful");
            } else {
                response.setStatus(ResponseStatus.FAILURE);
                response.setMessage("Sign up failed");
            }

            return response;

        } catch (Exception e) {
            response.setMessage("Failed to register user. Error: " + e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }
    }

    public LoginUserResponseDTO loginUser(SignupUserRequestDTO req) {

        LoginUserResponseDTO response = new LoginUserResponseDTO();
        try {

            User user = userService.loginUser(req.getEmail(), req.getPassword());

            if (user.getId() > 0) {

                response.setUser(user);
                response.setStatus(ResponseStatus.SUCCESS);
                response.setMessage("Login successful");
            } else {

                response.setStatus(ResponseStatus.FAILURE);
                response.setMessage("Login failed");
            }

            return response;

        } catch (Exception e) {
            response.setMessage("Failed to register user. Error: " + e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }
    }
}
