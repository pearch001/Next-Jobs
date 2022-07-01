package com.NextJobs.NextJobsapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String FACEBOOK_GRAPH_API_BASE = "https://graph.facebook.com";
}
