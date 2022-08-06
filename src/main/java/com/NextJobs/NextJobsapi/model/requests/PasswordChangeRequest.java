package com.NextJobs.NextJobsapi.model.requests;

import com.sun.istack.NotNull;

public class PasswordChangeRequest {
    @NotNull
    private String text;
    @NotNull
    private String password;
    @NotNull
    private String userame;

    public String getUserame() {
        return userame;
    }

    public void setUserame(String userame) {
        this.userame = userame;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
