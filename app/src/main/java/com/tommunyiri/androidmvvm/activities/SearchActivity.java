package com.tommunyiri.androidmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.tommunyiri.androidmvvm.R;
import com.tommunyiri.androidmvvm.adapters.TVShowsAdapter;
import com.tommunyiri.androidmvvm.databinding.ActivitySearchBinding;
import com.tommunyiri.androidmvvm.listeners.TVShowsListener;
import com.tommunyiri.androidmvvm.models.TVShow;
import com.tommunyiri.androidmvvm.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class SearchActivity extends AppCompatActivity implements TVShowsListener {
    private ActivitySearchBinding activitySearchBinding;
    private SearchViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private  int totalAvailablePages = 1;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding= DataBindingUtil.setContentView(this,R.layout.activity_search);
        doInitialization();
    }

    private void doInitialization(){
        activitySearchBinding.imageBack.setOnClickListener(v -> {
            onBackPressed();
        });
        activitySearchBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows,this);
        activitySearchBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activitySearchBinding.inputSSearch
    }

    private void searchTVShows(String query) {
        toggleLoading();
        viewModel.searchTVShow(query,currentPage).observe(this, tvShowsResponse -> {
            //Toast.makeText(getApplicationContext(), "Total pages: " + mostPopularTVShowsResponse.getTotalPages(), Toast.LENGTH_SHORT).show();
            toggleLoading();
            if(tvShowsResponse!=null){
                totalAvailablePages=tvShowsResponse.getTotalPages();
                if(tvShowsResponse.getTvShows()!=null){
                    int oldCount = tvShows.size();
                    tvShows.addAll(tvShowsResponse.getTvShows());
                    //tvShowsAdapter.notifyDataSetChanged();
                    tvShowsAdapter.notifyItemRangeInserted(oldCount,tvShows.size());
                }
            }
        });
    }

    private void toggleLoading(){
        if(currentPage==1){
            if(activitySearchBinding.getIsLoading()!=null&&activitySearchBinding.getIsLoading()){
                activitySearchBinding.setIsLoading(false);
            }else{
                activitySearchBinding.setIsLoading(true);
            }
        }else{
            if(activitySearchBinding.getIsLoadingMore()!=null&&activitySearchBinding.getIsLoadingMore()){
                activitySearchBinding.setIsLoadingMore(false);
            }else{
                activitySearchBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent=new Intent(this,TVShowDetailsActivity.class);
        intent.putExtra("tvShow",tvShow);
        startActivity(intent);
    }
}