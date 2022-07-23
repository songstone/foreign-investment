package com.example.service;

import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long registerUser(String name, String email, String birthday) {
        LocalDate birth = mapToDate(birthday);
        if (userRepository.findByNameAndBirth(name, birth).isPresent()) {
            throw new IllegalArgumentException("DUPLICATED_USER");
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .birth(birth)
                .build();
        return userRepository.save(user).getId();
    }

    public User getUser(String name, String birthday) {
        LocalDate birth = mapToDate(birthday);
        User user = userRepository.findByNameAndBirth(name,birth).orElseThrow(
                () -> new IllegalArgumentException("USER_NOT_FOUND")
        );
        return user;
    }

    private LocalDate mapToDate(String birthday) {
        return LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
    }
}
