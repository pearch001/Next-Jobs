package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.exceptions.InternalServerException;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.google.GoogleUser;
import com.NextJobs.NextJobsapi.model.entities.linkedin.Element;
import com.NextJobs.NextJobsapi.model.entities.linkedin.linkedinuser.LinkedInUser;
import com.NextJobs.NextJobsapi.model.enums.AppUserRole;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;

import com.NextJobs.NextJobsapi.utils.LinkedInClient;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;


@Service
@Slf4j
public class LinkedInService {
    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired private JwtTokenUtil tokenProvider;

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private LinkedInClient linkedInClient;

    public String loginUser(String code) {
        var lnAccessToken = linkedInClient.getlnAccessToken(code);

        var userEmail = linkedInClient.getUserEmail(lnAccessToken);

        var linkedInUser = linkedInClient.getUserProfile(lnAccessToken);

        return appUserDao.findByEmail(userEmail.getHandle().emailAddress)
                .or(() -> Optional.ofNullable(appUserService.registerUser(convertTo(userEmail, linkedInUser), AppUserRole.NEWUSER)))
                .map(tokenProvider::generateToken)
                .orElseThrow(() ->
                        new InternalServerException("unable to login linkedIn user id " + userEmail.getHandle().emailAddress));
    }
    private AppUser convertTo(Element userEmail, LinkedInUser linkedInUser) {
        return AppUser.builder()
                .email(userEmail.getHandle().emailAddress)
                .firstName(linkedInUser.localizedFirstName)
                .lastName(linkedInUser.localizedLastName)
                .enabled(true)
                .locked(false)
                .password(generatePassword(8))
                .imageUrl(linkedInUser.profilePicture.displayImage)
                .build();
    }

    private String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }
}
