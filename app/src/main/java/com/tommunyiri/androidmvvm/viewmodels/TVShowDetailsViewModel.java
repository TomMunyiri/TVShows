package com.tommunyiri.androidmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tommunyiri.androidmvvm.repositories.TVShowDetailsRepository;
import com.tommunyiri.androidmvvm.responses.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends ViewModel {
    private TVShowDetailsRepository tvShowDetailsRepository;

    public TVShowDetailsViewModel(TVShowDetailsRepository tvShowDetailsRepository) {
        this.tvShowDetailsRepository = tvShowDetailsRepository;
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }
}
