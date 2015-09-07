package com.udacity.hoanv.popularmovie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.hoanv.popularmovie.Entity.MovieDetail;
import com.udacity.hoanv.popularmovie.service.MovieDBService;
import com.udacity.hoanv.popularmovie.service.WebService;

import java.io.IOException;

import retrofit.Call;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment{

    private final static String TAG_LOG = DetailActivityFragment.class.getSimpleName();
    private Long idMovie;
    private ImageView posterImageView = null;
    private TextView movieTitleView = null;
    private TextView manufacturerYearView = null;
    private TextView runtimeView = null;
    private TextView voteAverageView = null;
    private TextView overviewTextView = null;
    private DialogFragment dialogFragment;

    public DetailActivityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            idMovie = getActivity().getIntent().getLongExtra(Constant.EXTRA_MOVIEID, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        updateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detailFragment = inflater.inflate(R.layout.fragment_detail, container, false);
        if (detailFragment != null) {
            movieTitleView = (TextView)detailFragment.findViewById(R.id.movie_title);
            runtimeView = (TextView) detailFragment.findViewById(R.id.movie_runtime);
            voteAverageView = (TextView) detailFragment.findViewById(R.id.movie_vote_average);
            posterImageView = (ImageView) detailFragment.findViewById(R.id.movie_poster);
            manufacturerYearView = (TextView) detailFragment.findViewById(R.id.movie_manufacturer_year);
            overviewTextView = (TextView) detailFragment.findViewById(R.id.movie_overview);
        }
        return detailFragment;
    }

    public class DetailMovieAsyncTask extends AsyncTask<Long, Void, MovieDetail>{

        @Override
        protected MovieDetail doInBackground(Long... params) {
            if(params == null || params.length == 0){
                Log.i(TAG_LOG, "DetailMovieAsyncTask has been run without params!");
                return null;
            }

            dialogFragment = ProgressDialogFragment.getInstance();
            dialogFragment.show(getFragmentManager(), "Loading");

            //Call API from TheMovieDB by Retrofit Library
            MovieDBService movieDBService = WebService.getMovieDBService();
            Call<MovieDetail> call = movieDBService.getMovieDetail(params[0], getString(R.string.api_key));
            try {
                return call.execute().body();
            } catch (IOException e) {
                Log.e(TAG_LOG, "ERROR", e);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MovieDetail detail) {
            super.onPostExecute(detail);
            if (detail != null) {
                Picasso.with(getActivity()).load(Constant.THUMBNAIL_BASE_URL + detail.getPosterPath()).into(posterImageView);
                movieTitleView.setText(detail.getTitle());
                runtimeView.setText(Integer.toString(detail.getRuntime()) + "min");
                manufacturerYearView.setText(MovieUtils.getReadableManufacturerYear(detail.getReleaseDate()));
                voteAverageView.setText(detail.getVoteAverage() + "/10");
                overviewTextView.setText(detail.getOverview());
                dialogFragment.dismiss();
            }
        }
    }

    public class TrailerMovieAsyncTask extends AsyncTask<Long, Void, String>{

        @Override
        protected String doInBackground(Long... longs) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public void updateView(){
        DetailMovieAsyncTask detailTask = new DetailMovieAsyncTask();
        TrailerMovieAsyncTask trailerTask = new TrailerMovieAsyncTask();
        if (idMovie != null) {
            detailTask.execute(idMovie);
            trailerTask.execute(idMovie);
        }
    }

}
