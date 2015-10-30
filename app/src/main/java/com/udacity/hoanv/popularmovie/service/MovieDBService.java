package com.udacity.hoanv.popularmovie.service;

import com.udacity.hoanv.popularmovie.Constant;
import com.udacity.hoanv.popularmovie.Entity.Movie;
import com.udacity.hoanv.popularmovie.Entity.PosterMovieList;
import com.udacity.hoanv.popularmovie.Entity.ReviewMovieList;
import com.udacity.hoanv.popularmovie.Entity.VideoMovieList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by HoaNV on 9/7/15.
 */
public interface MovieDBService {

    @GET("discover/movie")
    Call<PosterMovieList> listMovieThumbnail(@Query(Constant.API_KEY) String api_key, @Query(Constant.SORT_BY) String sortBy);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") Long id, @Query(Constant.API_KEY) String api_key);

    @GET("movie/{id}/videos")
    Call<VideoMovieList> getListVideoMovie(@Path("id") Long id, @Query(Constant.API_KEY) String api_key);

    @GET("movie/{id}/reviews")
    Call<ReviewMovieList> getListReviewMovie(@Path("id") Long id, @Query(Constant.API_KEY) String api_key);
}
