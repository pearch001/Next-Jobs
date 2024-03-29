package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.exceptions.InternalServerException;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.facebook.FacebookUser;
import com.NextJobs.NextJobsapi.model.entities.google.GoogleUser;
import com.NextJobs.NextJobsapi.model.enums.AppUserRole;
import com.NextJobs.NextJobsapi.utils.GoogleClient;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class GoogleService {

    @Autowired
    private AppUserServiceImpl appUserService;
    @Autowired private JwtTokenUtil tokenProvider;

    @Autowired
    private GoogleClient googleClient;

    @Autowired
    private AppUserDao appUserDao;

    public String loginUser(String gAccessToken) throws GeneralSecurityException, IOException {
        var googleUser = googleClient.getUser(gAccessToken);

        return appUserDao.findByEmail(googleUser.getEmail())
                .or(() -> Optional.ofNullable(appUserService.registerUser(convertTo(googleUser), AppUserRole.NEWUSER)))
                .map(tokenProvider::generateToken)
                .orElseThrow(() ->
                        new InternalServerException("unable to login gmail user email " + googleUser.getEmail()));
    }

    private AppUser convertTo(GoogleUser googleUser) {
        return AppUser.builder()
                .email(googleUser.getEmail())
                .firstName(googleUser.getFirstName())
                .lastName(googleUser.getLastName())
                .enabled(true)
                .password(generatePassword(8))
                .imageUrl(googleUser.getPicture())
                .locked(false)
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
