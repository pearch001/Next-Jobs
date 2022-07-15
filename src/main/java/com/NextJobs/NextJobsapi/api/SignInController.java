package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.exceptions.AuthenticationFailException;
import com.NextJobs.NextJobsapi.model.JwtRequest;
import com.NextJobs.NextJobsapi.model.JwtResponse;
import com.NextJobs.NextJobsapi.model.requests.FacebookLoginRequest;
import com.NextJobs.NextJobsapi.model.requests.GoogleLoginRequest;
import com.NextJobs.NextJobsapi.services.AppUserServiceImpl;
import com.NextJobs.NextJobsapi.services.FacebookService;
import com.NextJobs.NextJobsapi.services.GoogleService;
import com.NextJobs.NextJobsapi.services.LinkedInService;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@RequestMapping("/nextjobs/v1")
@CrossOrigin("*")
@RestController
public class SignInController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private LinkedInService linkedInService;
    @Autowired
    private GoogleService googleService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value="/signin")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            log.info("login");
        }catch (BadCredentialsException e){

            throw new AuthenticationFailException("Incorrect username or password");
        }
        final UserDetails userDetails = appUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    @PostMapping("/facebook/signin")
    public  ResponseEntity<?> facebookAuth(@RequestBody FacebookLoginRequest facebookLoginRequest) {
        log.info("facebook login {}", facebookLoginRequest);
        String token = facebookService.loginUser(facebookLoginRequest.getAccessToken());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/google/signin")
    public  ResponseEntity<?> googleAuth(@RequestBody GoogleLoginRequest googleLoginRequest) throws GeneralSecurityException, IOException {
        log.info("google login {}", googleLoginRequest);
        String token = googleService.loginUser(googleLoginRequest.getAccessToken());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/linkedIn/signin")
    public  ResponseEntity<?> linkedInAuth(@RequestParam(required = false) String state,@RequestParam(required = false) String code) {
        log.info("linkedIn login {}", code);
        String token = linkedInService.loginUser(code);
        return ResponseEntity.ok(token);
    }
}
