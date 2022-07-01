package com.NextJobs.NextJobsapi.utils;
import com.NextJobs.NextJobsapi.model.entities.facebook.FacebookUser;
import com.NextJobs.NextJobsapi.model.entities.google.GoogleUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENT_ID = "274465433189-bae050vqccq2ul57665m946fm64opnf4.apps.googleusercontent.com";

    private HttpTransport transport;
    private JsonFactory jsonFactory;

    public GoogleUser getUser(String accessToken) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)

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
            throw new In
        }

    }


}
