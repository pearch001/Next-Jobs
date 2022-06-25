package com.NextJobs.NextJobsapi.utils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        if (s == null || s.isEmpty()){
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(s).matches()){

            return true;
        }else {

            return false;
        }
    }
}
