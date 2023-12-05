package com.example.cookie.controller;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse,
                        HttpSession httpSession){
        return accountService.login(loginRequest,httpServletResponse);
    }
}
