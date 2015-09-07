package com.udacity.hoanv.popularmovie.Entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HoaNV on 9/7/15.
 */
public class DiscoverMovieResult implements Serializable {

    private Integer page;

    private ArrayList<DiscoverMovie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<DiscoverMovie> getResults() {
        return results;
    }

    public void setResults(ArrayList<DiscoverMovie> results) {
        this.results = results;
    }
}
