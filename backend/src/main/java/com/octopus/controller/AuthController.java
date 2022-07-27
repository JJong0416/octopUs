package com.octopus.controller;

import com.octopus.domain.dto.LoginDto;
import com.octopus.domain.dto.TokenDto;
import com.octopus.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 권한에 대한 Controller (Login 포함)
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/domain")
    public ResponseEntity<TokenDto> loginFromDomain(@Valid @RequestBody LoginDto loginDto) {
        String jwt = authService.createJwtString(loginDto);

        return new ResponseEntity<>(
                new TokenDto(jwt), authService.createJwtHttpHeader(jwt), HttpStatus.OK);
    }
}