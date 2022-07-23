package com.example.controller;

import com.example.domain.user.User;
import com.example.dto.RegisterUser;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public Long registerUser(@RequestBody RegisterUser request) {
        return userService.registerUser(request.getName(), request.getEmail(), request.getBirth());
    }

    @GetMapping("/user")
    public User getUser(
            @RequestParam String name,
            @RequestParam String birth
    ) {
        return userService.getUser(name, birth);
    }
}
