package com.tommunyiri.androidmvvm.network;

import com.tommunyiri.androidmvvm.responses.TVShowDetailsResponse;
import com.tommunyiri.androidmvvm.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("most-popular")
    Call<TVShowsResponse> getMostPopularTVShows(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId );
}
