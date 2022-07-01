package com.NextJobs.NextJobsapi.model.entities.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUser {
    private Long id;
    private String iss;
    private String sub;
    private String azp;
    private String aud;
    private String iat;
    private String exp;
    private String email;
    private String email_verified;
    private String name;
    // These six fields are included in all Google ID Tokens.


         "picture": "https://lh4.googleusercontent.com/-kYgzyAWpZzJ/ABCDEFGHI/AAAJKLMNOP/tIXL9Ir44LE/s99-c/photo.jpg",
         "given_name": "Test",
         "family_name": "User",
         "locale": "en"
}
