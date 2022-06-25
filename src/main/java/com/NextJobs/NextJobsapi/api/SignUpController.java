package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.requests.SignUpRequest;
import com.NextJobs.NextJobsapi.services.SignUpServiceImpl;
import com.NextJobs.NextJobsapi.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/nextjobs/v1/signup")
@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpService;

    @PostMapping
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpRequest request) {
        signUpService.register(request);
        return new ResponseEntity<>(new ApiResponse(true,"Student registered"), HttpStatus.ACCEPTED);

    }
}
