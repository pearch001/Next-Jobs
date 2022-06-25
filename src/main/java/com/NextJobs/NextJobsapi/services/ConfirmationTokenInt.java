package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.model.entities.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenInt {

    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}
