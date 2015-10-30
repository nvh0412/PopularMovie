package com.udacity.hoanv.popularmovie.Entity;

import java.util.List;

/**
 * Created by HoaNV on 10/29/15.
 */
public class ReviewMovieList {

    private long id;
    private int page;
    private List<ReviewMovie> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ReviewMovie> getResults() {
        return results;
    }

    public void setResults(List<ReviewMovie> results) {
        this.results = results;
    }
}
