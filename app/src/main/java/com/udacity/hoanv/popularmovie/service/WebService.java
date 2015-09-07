package com.udacity.hoanv.popularmovie.service;

import com.udacity.hoanv.popularmovie.Constant;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by HoaNV on 9/7/15.
 */
public class WebService {

    public static MovieDBService getMovieDBService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MovieDBService.class);
    }

}
