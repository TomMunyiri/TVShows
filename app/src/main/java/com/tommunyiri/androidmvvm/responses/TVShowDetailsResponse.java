package com.tommunyiri.androidmvvm.responses;

import com.google.gson.annotations.SerializedName;
import com.tommunyiri.androidmvvm.models.TVShowDetails;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
