package com.logicea.cards.controllers;

import com.logicea.cards.domain.dto.LoginDto;
import com.logicea.cards.domain.models.AuthResponse;
import com.logicea.cards.handlers.ResponseHandler;
import com.logicea.cards.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private  final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginWithUsernameAndPassword(@RequestBody LoginDto loginDto) {
        AuthResponse authResponse = userService.loginWithUsernameAndPassword(loginDto);
        return ResponseHandler.generateResponse("Login Successful", HttpStatus.OK, authResponse);
    }

}
