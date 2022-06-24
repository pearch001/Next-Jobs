package com.NextJobs.NextJobsapi.config;

import com.NextJobs.NextJobsapi.model.JwtRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private  AuthenticationManager authenticationManager;

    private JwtRequest jwtRequest;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager1) {

        this.authenticationManager = authenticationManager1;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("UserName is: {} ", username);
        log.info("Password is: {} ", password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        // Authenticate the user
        // if authentication is successful successfulAuthentication is called
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    public Authentication authentication(JwtRequest request) throws AuthenticationException {

        String username = request.getUsername();
        String password = request.getPassword();
        log.info("UserName is: {} ", username);
        log.info("Password is: {} ", password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        // Authenticate the user
        // if authentication is successful successfulAuthentication is called
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Getting the successfully authenticated user
        User user = (User)authResult.getPrincipal();
        //Algorithm to sign the json web token and refresh token
        String jwtSecret = SecurityConstants.SECRET;
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        //Creating tokens
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("access_token",accessToken);
        response.setHeader("refresh_token",refreshToken);
    }
}
