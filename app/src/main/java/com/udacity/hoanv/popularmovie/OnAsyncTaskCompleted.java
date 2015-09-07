package com.udacity.hoanv.popularmovie;

import com.udacity.hoanv.popularmovie.Entity.DiscoverMovie;

import java.util.List;

/**
 * Created by HoaNV on 9/7/15.
 */
public interface OnAsyncTaskCompleted {

    void onTaskCompleted(List<DiscoverMovie> movies);

}
