package com.udacity.hoanv.popularmovie.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoaNV on 9/7/15.
 */
public class DiscoverMovie {

    private Integer id;

    @SerializedName("poster_path")
    private String posterPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
