package com.local.accounttask.controllers;

import com.local.accounttask.dto.CreateLoginRequest;
import com.local.accounttask.dto.TokenDto;
import com.local.accounttask.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody CreateLoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.login(loginRequest));
    }


}
