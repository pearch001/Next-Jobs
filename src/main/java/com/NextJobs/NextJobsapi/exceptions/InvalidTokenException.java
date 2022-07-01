package com.NextJobs.NextJobsapi.exceptions;

public class InvalidTokenException extends IllegalArgumentException {
    public InvalidTokenException(String s) {
        super(s);
    }
}
