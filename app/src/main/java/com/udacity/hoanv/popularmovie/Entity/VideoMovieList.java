package com.udacity.hoanv.popularmovie.Entity;

import java.util.List;

/**
 * Created by HoaNV on 10/29/15.
 */
public class VideoMovieList {

    private long id;

    private List<VideoMovie> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<VideoMovie> getResults() {
        return results;
    }

    public void setResults(List<VideoMovie> results) {
        this.results = results;
    }
}
