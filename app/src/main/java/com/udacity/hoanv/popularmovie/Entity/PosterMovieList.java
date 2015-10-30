package com.udacity.hoanv.popularmovie.Entity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by HoaNV on 9/7/15.
 */
public class PosterMovieList implements Serializable {

    private Integer page;

    private List<PosterMovie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<PosterMovie> getResults() {
        return results;
    }

    public void setResults(List<PosterMovie> results) {
        this.results = results;
    }

}
