package com.NextJobs.NextJobsapi.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
    public AuthenticationFailException(String s) {
        super(s);
    }
}
