package com.octopus.controller;

import com.octopus.domain.User;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody SignUpDto signUpDto
    ) {
        return ResponseEntity.ok(userService.signup(signUpDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithUserId().get());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<User> getUserInfo(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserWithUserId(userId).get());
    }
}