package com.tommunyiri.androidmvvm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tommunyiri.androidmvvm.R;
import com.tommunyiri.androidmvvm.adapters.TVShowsAdapter;
import com.tommunyiri.androidmvvm.databinding.ActivityMainBinding;
import com.tommunyiri.androidmvvm.listeners.TVShowsListener;
import com.tommunyiri.androidmvvm.models.TVShow;
import com.tommunyiri.androidmvvm.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {
    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage =1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows,this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        //activityMainBinding.setIsLoading(true);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)){
                    if(currentPage<=totalAvailablePages){
                        currentPage+=1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchlist.setOnClickListener(v -> {
            Intent intent=new Intent(this,WatchlistActivity.class);
            startActivity(intent);
        });
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
            //Toast.makeText(getApplicationContext(), "Total pages: " + mostPopularTVShowsResponse.getTotalPages(), Toast.LENGTH_SHORT).show();
            toggleLoading();
            if(mostPopularTVShowsResponse!=null){
                totalAvailablePages=mostPopularTVShowsResponse.getTotalPages();
                if(mostPopularTVShowsResponse.getTvShows()!=null){
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    //tvShowsAdapter.notifyDataSetChanged();
                    tvShowsAdapter.notifyItemRangeInserted(oldCount,tvShows.size());
                }
            }
        });
    }

    private void toggleLoading(){
        if(currentPage==1){
            if(activityMainBinding.getIsLoading()!=null&&activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else{
                activityMainBinding.setIsLoading(true);
            }
        }else{
            if(activityMainBinding.getIsLoadingMore()!=null&&activityMainBinding.getIsLoadingMore()){
                activityMainBinding.setIsLoadingMore(false);
            }else{
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent=new Intent(getApplicationContext(),TVShowDetailsActivity.class);
        /*intent.putExtra("id",tvShow.getId());
        intent.putExtra("name",tvShow.getName());
        intent.putExtra("startDate",tvShow.getStartDate());
        intent.putExtra("country",tvShow.getCountry());
        intent.putExtra("network",tvShow.getNetwork());
        intent.putExtra("status",tvShow.getStatus());*/
        intent.putExtra("tvShow",tvShow);
        startActivity(intent);
    }
}