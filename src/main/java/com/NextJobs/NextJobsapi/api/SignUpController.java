package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.requests.PasswordChangeRequest;
import com.NextJobs.NextJobsapi.model.requests.RecoveryCheckRequest;
import com.NextJobs.NextJobsapi.model.requests.SignUpRequest;
import com.NextJobs.NextJobsapi.services.SignUpServiceImpl;
import com.NextJobs.NextJobsapi.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RequestMapping("/nextjobs/v1/signup")
@CrossOrigin("*")
@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpService;

    @PostMapping
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpRequest request) {
        String Link = signUpService.register(request);
        return new ResponseEntity<>(new ApiResponse(true,"User created"), HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "confirm")
    public ResponseEntity<Void> confirmToken(@RequestParam("token") String token) {
        signUpService.confirmToken(token);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://chic-fenglisu-b3e414.netlify.app")).build();

    }
    @GetMapping("/recover/first/{email}/")
    public ResponseEntity<ApiResponse> recoverfirst(@PathVariable String email){
        log.info("IN RECOVERY");
        if (signUpService.recovery(email)){
            return new ResponseEntity<>(new ApiResponse(true,"Recovery Email sent"), HttpStatus.ACCEPTED);
        }else   {
            return new ResponseEntity<>(new ApiResponse(false,"Email does not exist"), HttpStatus.ACCEPTED);
        }

    }
    @PostMapping("/recover/check")
    public ResponseEntity<ApiResponse> recoverCheck(@RequestBody RecoveryCheckRequest request){
        log.info("IN RECOVERY Check");
        if (signUpService.recoveryCheck(request)){
            return new ResponseEntity<>(new ApiResponse(true,"Recovery text is correct"), HttpStatus.ACCEPTED);
        }else   {
            return new ResponseEntity<>(new ApiResponse(false,"Recovery text is wrong"), HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/recover/second")
    public ResponseEntity<ApiResponse> recoverComplete(@RequestBody PasswordChangeRequest request){
        log.info("IN RECOVERY 2nd phase");
        signUpService.recoverysecond(request);
        return new ResponseEntity<>(new ApiResponse(true,"Recovery Complete"), HttpStatus.ACCEPTED);
    }

}
