package com.tommunyiri.androidmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.tommunyiri.androidmvvm.R;
import com.tommunyiri.androidmvvm.adapters.TVShowsAdapter;
import com.tommunyiri.androidmvvm.databinding.ActivityMainBinding;
import com.tommunyiri.androidmvvm.models.TVShow;
import com.tommunyiri.androidmvvm.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.setIsLoading(true);
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse -> {
            //Toast.makeText(getApplicationContext(), "Total pages: " + mostPopularTVShowsResponse.getTotalPages(), Toast.LENGTH_SHORT).show();
            activityMainBinding.setIsLoading(false);
            if(mostPopularTVShowsResponse!=null){
                if(mostPopularTVShowsResponse.getTvShows()!=null){
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}