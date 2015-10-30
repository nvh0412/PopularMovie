package com.udacity.hoanv.popularmovie.api;

import android.test.AndroidTestCase;

import com.udacity.hoanv.popularmovie.Entity.Movie;
import com.udacity.hoanv.popularmovie.Entity.PosterMovieList;
import com.udacity.hoanv.popularmovie.Entity.ReviewMovieList;
import com.udacity.hoanv.popularmovie.Entity.VideoMovieList;
import com.udacity.hoanv.popularmovie.R;
import com.udacity.hoanv.popularmovie.service.MovieDBService;
import com.udacity.hoanv.popularmovie.service.WebService;

import java.io.IOException;

import retrofit.Call;

/**
 * Created by HoaNV on 10/29/15.
 */
public class TestMovieAPI extends AndroidTestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testListMovieThumbnail() throws IOException {
        MovieDBService movieDBService = WebService.getMovieDBService();
        assertNotNull("Webservice returned null", movieDBService);

        Call<PosterMovieList> posterListCall = movieDBService.listMovieThumbnail(
                mContext.getString(R.string.api_key),
                mContext.getString(R.string.pref_sort_by_popular));
        assertNotNull("Call can't null", posterListCall);

        PosterMovieList posterMovieList = posterListCall.execute().body();
        assertNotNull("API response error!", posterMovieList);
        assertTrue("Page can't equals to 0", posterMovieList.getPage() > 0);
        assertFalse("Poster list can't empty", posterMovieList.getResults().isEmpty());
    }

    private static final Long TEST_MOVIE_ID = 550l;

    public void testGetMovieDetail() throws IOException {
        MovieDBService movieDBService = WebService.getMovieDBService();
        assertNotNull("Webservice returned null", movieDBService);

        Call<Movie> movieCall = movieDBService.getMovieDetail(
                TEST_MOVIE_ID,
                mContext.getString(R.string.api_key));
        assertNotNull("Call can't null", movieCall);

        Movie movie = movieCall.execute().body();
        assertNotNull("API response error!", movie);
        assertEquals("Id of movie has to equals to Constant", TEST_MOVIE_ID, movie.getId());
    }

    public void testGetListVideoMovie() throws IOException {
        MovieDBService movieDBService = WebService.getMovieDBService();
        assertNotNull("Webservice returned null", movieDBService);

        Call<VideoMovieList> videoMovieCall = movieDBService.getListVideoMovie(
                TEST_MOVIE_ID,mContext.getString(R.string.api_key));
        VideoMovieList videoMovieList = videoMovieCall.execute().body();
        assertNotNull("API response error!", videoMovieList);
        assertFalse("List trailer of movie " + TEST_MOVIE_ID + " can't be null",
                videoMovieList.getResults().isEmpty());
    }

    public void testGetListReviewMovie() throws IOException {
        MovieDBService movieDBService = WebService.getMovieDBService();
        assertNotNull("Webservice returned null", movieDBService);

        Call<ReviewMovieList> reviewMovieListCall = movieDBService.getListReviewMovie(
                49026l,
                mContext.getString(R.string.api_key));
        ReviewMovieList reviewMovieList = reviewMovieListCall.execute().body();
        assertNotNull("API response error!", reviewMovieList);
        assertFalse("List reviews of movie " + 49026l + " can't be null",
                reviewMovieList.getResults().isEmpty());
    }

}
