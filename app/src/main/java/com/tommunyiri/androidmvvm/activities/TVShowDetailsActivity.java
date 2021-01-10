package com.tommunyiri.androidmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tommunyiri.androidmvvm.R;
import com.tommunyiri.androidmvvm.adapters.ImageSliderAdapter;
import com.tommunyiri.androidmvvm.databinding.ActivityTVShowDetailsBinding;
import com.tommunyiri.androidmvvm.viewmodels.TVShowDetailsViewModel;

public class TVShowDetailsActivity extends AppCompatActivity {
    private ActivityTVShowDetailsBinding activityTVShowDetailsBinding;
    private TVShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTVShowDetailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_t_v_show_details);
        doInitialization();
    }

    private void doInitialization(){
        tvShowDetailsViewModel= new ViewModelProvider(this).get(TVShowDetailsViewModel.class);
        getTVShowDetails();
    }

    private void getTVShowDetails(){
        activityTVShowDetailsBinding.setIsLoading(true);
        String tvShowId = String.valueOf(getIntent().getIntExtra("id",-1));
        tvShowDetailsViewModel.getTVShowDetails(tvShowId).observe(this,tvShowDetailsResponse -> {
            activityTVShowDetailsBinding.setIsLoading(false);
            if(tvShowDetailsResponse.getTvShowDetails()!=null){
                if(tvShowDetailsResponse.getTvShowDetails().getPictures()!=null){
                    loadImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                }
            }
        });
    }

    private void loadImageSlider(String[] sliderImages){
        activityTVShowDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTVShowDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTVShowDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);
    }
}