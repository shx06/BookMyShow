package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.LoginUserResponseDTO;
import com.example.bookmyshow.dtos.SignupUserRequestDTO;
import com.example.bookmyshow.dtos.SignupUserResponseDTO;
import com.example.bookmyshow.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired
    UserController userController;

    @Override
    public void run(String... args) throws Exception {
//        SignupUserRequestDTO req = new SignupUserRequestDTO();
//        req.setName("John Doe");
//        req.setEmail("john.doe@gmail.com");
//        req.setPassword("abc");
//
//        SignupUserResponseDTO response = userController.signUpUser(req);
//
//        System.out.println("Messsage: " + response.getMessage());
//        System.out.println("Status: " + response.getStatus());
//        System.out.println("UserID: " + response.getUserId());


        SignupUserRequestDTO req = new SignupUserRequestDTO();
        req.setEmail("john.doe@gmail.com");
        req.setPassword("abc");

        LoginUserResponseDTO response = userController.loginUser(req);

        System.out.println("Messsage: " + response.getMessage());
        System.out.println("Status: " + response.getStatus());
        System.out.println("User: " + response.getUser().getId() + " " + response.getUser().getName() + " " + response.getUser().getEmail());
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

}
