package com.example.bookmyshow.serivces;

import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int signUpUser(String name, String email, String password) {
        // check if user already exists

        Optional<User> userOptional = userRepo.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public User loginUser(String email, String password) {
        // check if user already not exists

        Optional<User> optionalUser = userRepo.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
}
