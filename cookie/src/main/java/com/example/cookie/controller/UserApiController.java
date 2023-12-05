package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;


@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;


    @GetMapping("/me")
    public UserDto me(HttpServletRequest httpServletRequest,
                       @CookieValue(name = "authorization-cookie", required = false) String authorizationCookie
    ){
        log.info("authorizationCookie : {}", authorizationCookie);
        var optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get();
    }


    @GetMapping("/me2")
    public UserDto me2(@RequestHeader(name = "authorization", required = false)  String authorizationCookie
    ){
        log.info("authorizationCookie : {}", authorizationCookie);
        var optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get();
    }
}
