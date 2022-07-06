package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.exceptions.UserExistException;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.ConfirmationToken;
import com.NextJobs.NextJobsapi.model.enums.AppUserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements UserDetailsService, AppUserServiceInt {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConfirmationTokenImpl confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserDao.findByEmail(s).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Override
    public String signup(AppUser appUser) {
        boolean userExists = appUserDao
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new UserExistException("email already taken");
        }



        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        appUserDao.save(appUser);

        String token = UUID.randomUUID().toString();
        log.info("Before Confirmation Token");
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        return token;
    }
    public void enableAppUser(String email) {
        appUserDao.enableAppUser(email);
    }

    public void addImageUrl(String email, String imageUrl) {
        appUserDao.addImageUrl(email,imageUrl);
    }

    public AppUser registerUser(AppUser user, AppUserRole role) {
        log.info("registering user {}", user.getUsername());

        if(appUserDao.findByEmail(user.getEmail()).isPresent()) {
            log.warn("username {} already exists.", user.getUsername());

            throw new UserExistException(
                    String.format("username %s already exists", user.getUsername()));
        }


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAppUserRole(role);


        return appUserDao.save(user);
    }






}
