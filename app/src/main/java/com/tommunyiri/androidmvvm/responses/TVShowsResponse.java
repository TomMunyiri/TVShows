package com.tommunyiri.androidmvvm.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tommunyiri.androidmvvm.models.TVShow;

import java.util.List;

public class TVShowsResponse {
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pages")
    @Expose
    private Integer totalPages;
    @SerializedName("tv_shows")
    @Expose
    private List<TVShow> tvShows = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TVShow> tvShows) {
        this.tvShows = tvShows;
    }
}
