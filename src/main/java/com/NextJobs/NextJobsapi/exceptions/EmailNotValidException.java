package com.NextJobs.NextJobsapi.exceptions;

public class EmailNotValidException extends IllegalArgumentException{
    public EmailNotValidException(String s) {
        super(s);
    }
}
