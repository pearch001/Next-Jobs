package com.NextJobs.NextJobsapi.utils;

import com.NextJobs.NextJobsapi.model.entities.linkedin.Element;
import com.NextJobs.NextJobsapi.model.entities.linkedin.LinkedInUserEmail;
import com.NextJobs.NextJobsapi.model.entities.linkedin.linkedinuser.LinkedInUser;
import com.NextJobs.NextJobsapi.model.requests.LinkedInLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class LinkedInClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${linkedIn.clientId}")
    private String CLIENT_ID;

    @Value("${linkedIn.clientSecret}")
    private String CLIENT_Secret;

    @Value("${linkedIn.reirectUri}")
    private String RedirectUri;



    public String getlnAccessToken(String code)  {
        String url = "https://www.linkedin.com/oauth/v2/accessToken";

        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("redirect_uri", RedirectUri);
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_Secret);


        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<LinkedInLoginRequest> request = restTemplate.exchange(url, HttpMethod.GET, entity, LinkedInLoginRequest.class, params);
        log.info("Getting acces token from linkedIn: " + request.getBody().accessToken);
        return request.getBody().accessToken;


    }
    public Element getUserEmail(String lnAccessToken){
        String url = "https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))";
        var entity = linkedInApiCall(lnAccessToken);
        ResponseEntity<LinkedInUserEmail> userEmailResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, LinkedInUserEmail.class);
        log.info(Objects.requireNonNull(userEmailResponseEntity.getBody().getElements().get(0).getHandle().emailAddress));

        return userEmailResponseEntity.getBody().getElements().get(0);
    }

    public LinkedInUser getUserProfile(String lnAccessToken) {

        String url = "https://api.linkedin.com/v2/me";
        var entity = linkedInApiCall(lnAccessToken);
        ResponseEntity<LinkedInUser> email = restTemplate.exchange(url, HttpMethod.GET, entity, LinkedInUser.class);
        log.info(Objects.requireNonNull(email.getBody().getLocalizedFirstName()));
        return email.getBody();
    }

    private  HttpEntity linkedInApiCall(String lnAccessToken) {

        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ lnAccessToken);
        return new HttpEntity(headers);

    }
}
