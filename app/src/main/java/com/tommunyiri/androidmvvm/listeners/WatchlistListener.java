package com.tommunyiri.androidmvvm.listeners;

import com.tommunyiri.androidmvvm.models.TVShow;

public interface WatchlistListener {
    void onTVShowClicked(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow,int position);
}
