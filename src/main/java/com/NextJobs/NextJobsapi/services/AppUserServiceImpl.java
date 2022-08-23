package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.exceptions.UserExistException;
import com.NextJobs.NextJobsapi.model.dtos.userDto;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.ConfirmationToken;
import com.NextJobs.NextJobsapi.model.enums.AppUserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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


    public AppUser loadUserByEmail(String email) throws UsernameNotFoundException {
        return appUserDao.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public userDto loadUser() throws UsernameNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        return convertToUserDto(appUserDao.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found")));
    }

    @Override
    public String signup(AppUser appUser) {
        boolean userExists = appUserDao
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            var user = appUserDao
                    .findByEmail(appUser.getEmail()).get();
            log.info("Checking if user has been confirmed");
            if (appUser.getEnabled().equals(false) & appUser.getFirstName().equals(user.getFirstName()) &
                appUser.getLastName().equals(user.getLastName())){
                String token = UUID.randomUUID().toString();
                log.info("Sending confirmation token");
                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        user
                );

                confirmationTokenService.saveConfirmationToken(
                        confirmationToken);
                return token;
            }

            throw new UserExistException("User details already exist");
        }



        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        log.info("Saving user");
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

    public void changePassword(String password, String userName){
        AppUser appUser = appUserDao.findByEmail(userName).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
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
           return appUserDao.findByEmail(user.getEmail()).get();
        }


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAppUserRole(role);


        return appUserDao.save(user);
    }

    public userDto convertToUserDto(AppUser appUser){
        return  new userDto(appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(), appUser.getImageUrl());
    }






}
