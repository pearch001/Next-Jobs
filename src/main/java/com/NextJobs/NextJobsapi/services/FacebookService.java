package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.exceptions.InternalServerException;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.facebook.FacebookUser;
import com.NextJobs.NextJobsapi.model.enums.AppUserRole;
import com.NextJobs.NextJobsapi.utils.FacebookClient;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class FacebookService {

    @Autowired
    private FacebookClient facebookClient;
    @Autowired private AppUserServiceImpl appUserService;
    @Autowired private JwtTokenUtil tokenProvider;

    @Autowired
    private AppUserDao appUserDao;

    public String loginUser(String fbAccessToken) {
        var facebookUser = facebookClient.getUser(fbAccessToken);

        if (facebookUser.getEmail() == null){

            return appUserDao.findByEmail(facebookUser.getId() + "@facebook.com")
                    .or(() -> Optional.ofNullable(appUserService.registerUser(convertTo(facebookUser), AppUserRole.NEWUSER)))
                    .map(tokenProvider::generateToken)
                    .orElseThrow(() ->
                            new InternalServerException("unable to login facebook user id " + facebookUser.getId()));

        }

        return appUserDao.findByEmail(facebookUser.getEmail())
                .or(() -> Optional.ofNullable(appUserService.registerUser(convertTo(facebookUser), AppUserRole.NEWUSER)))
                .map(tokenProvider::generateToken)
                .orElseThrow(() ->
                        new InternalServerException("unable to login facebook user id " + facebookUser.getId()));
    }

    private AppUser convertTo(FacebookUser facebookUser) {
        if (facebookUser.getEmail() == null){
            return AppUser.builder()
                    .email(facebookUser.getId() + "@facebook.com")
                    .firstName(facebookUser.getFirstName())
                    .lastName(facebookUser.getLastName())
                    .password(generatePassword(8))
                    .enabled(true)
                    .locked(false)
                    .imageUrl(null)
                    .build();
        }
        return AppUser.builder()
                .email(facebookUser.getEmail())
                .firstName(facebookUser.getFirstName())
                .lastName(facebookUser.getLastName())
                .password(generatePassword(8))
                .enabled(true)
                .locked(false)
                .imageUrl(facebookUser.getPicture().getData().getUrl())
                .build();
    }

    private String generateUsername(String firstName, String lastName) {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%s.%s.%06d", firstName, lastName, number);
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
