package com.tommunyiri.androidmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tommunyiri.androidmvvm.R;
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
    }

    private void getTVShowDetails(){
        activityTVShowDetailsBinding.setIsLoading(true);
    }
}