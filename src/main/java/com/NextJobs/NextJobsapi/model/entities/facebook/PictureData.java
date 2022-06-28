package com.NextJobs.NextJobsapi.model.entities.facebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureData {

    private String height;
    private String width;
    private String url;
}
