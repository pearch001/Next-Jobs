package com.NextJobs.NextJobsapi.utils;
import com.NextJobs.NextJobsapi.exceptions.InvalidTokenException;
import com.NextJobs.NextJobsapi.model.entities.google.GoogleUser;
import com.google.api.client.auth.openidconnect.IdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Slf4j
@Service
public class GoogleClient {

    @Value("${google.clientId}")
    private String CLIENT_ID;

    private final HttpTransport transport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new GsonFactory();




    public GoogleUser getUser(String accessToken) throws GeneralSecurityException, IOException {


        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder( transport , jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                //.setAudience(Collections.singletonList("274465433189-bae050vqccq2ul57665m946fm64opnf4.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                .setAudience(Arrays.asList(CLIENT_ID))
                .build();

        // (Receive idTokenString by HTTPS POST
        GoogleIdToken idToken = verifier.verify(accessToken);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = payload.getEmailVerified();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

            return  GoogleUser.builder().email(email)
                    .firstName(givenName)
                    .lastName(familyName)
                    .locale(locale)
                    .picture(pictureUrl)
                    .build();

        } else {
            System.out.println("Invalid ID token.");
            throw new InvalidTokenException("Invalid ID token.");
        }

    }


}
