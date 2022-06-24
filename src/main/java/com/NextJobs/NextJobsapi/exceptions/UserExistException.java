package com.NextJobs.NextJobsapi.exceptions;

public class UserExistException extends IllegalStateException{
    public UserExistException(String s) {
        super(s);
    }
}
