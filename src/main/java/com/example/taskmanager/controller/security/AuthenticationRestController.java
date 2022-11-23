package com.example.taskmanager.controller.security;

import com.example.taskmanager.dto.AuthenticationRequestDto;
import com.example.taskmanager.model.User;
import com.example.taskmanager.security.JwtUserDetailsService;
import com.example.taskmanager.security.jwt.JwtTokenProvider;
import com.example.taskmanager.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, JwtUserDetailsService jwtUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(
//            @RequestBody
            AuthenticationRequestDto requestDto,
            HttpServletResponse response) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Cookie bearerCookie = new Cookie("Bearer", token);
            bearerCookie.setHttpOnly(Boolean.TRUE);
            bearerCookie.setMaxAge(300);
            response.addCookie(bearerCookie);
            System.out.println("отправляем токен " + token);
            if (token != null) {
                return "redirect:/records/user-" + user.getId() + "/";
            }


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return "redirect:/login-error";
    }
}
