package com.udacity.hoanv.popularmovie.service;

import com.udacity.hoanv.popularmovie.Constant;
import com.udacity.hoanv.popularmovie.Entity.DiscoverMovieResult;
import com.udacity.hoanv.popularmovie.Entity.MovieDetail;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by HoaNV on 9/7/15.
 */
public interface MovieDBService {

    @GET("discover/movie")
    Call<DiscoverMovieResult> listMovieThumbnail(@Query(Constant.API_KEY) String api_key, @Query(Constant.SORT_BY) String sortBy);

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") Long movieId, @Query(Constant.API_KEY) String api_key);

}
