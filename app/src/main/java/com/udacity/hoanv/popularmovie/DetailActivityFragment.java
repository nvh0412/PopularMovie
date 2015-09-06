package com.udacity.hoanv.popularmovie;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private final static String TAG_LOG = DetailActivityFragment.class.getSimpleName();
    private Long idMovie;
    private ImageView posterImageView = null;
    private TextView movieTitleView = null;
    private TextView manufacturerYearView = null;
    private TextView runtimeView = null;
    private TextView voteAverageView = null;
    private TextView overviewTextView = null;

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
            HttpURLConnection conn = null;
            BufferedReader bufferedReader = null;
            URL urlRequest = null;
            MovieDetail movieDetail = null;
            try {
                Uri uriBuilder = Uri.parse(Constant.MOVIE_DETAIL_URL).buildUpon()
                        .appendPath(params[0].toString())
                        .appendQueryParameter(Constant.API_KEY, getString(R.string.api_key))
                        .build();
                Log.d(TAG_LOG, "URL DETAIL : " +  uriBuilder.toString());
                urlRequest = new URL(uriBuilder.toString());
                conn = (HttpURLConnection) urlRequest.openConnection();

                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                String movieDetailJsonStr = buffer.toString();

                try {
                    movieDetail = MovieUtils.getDeailFromJson(movieDetailJsonStr);
                    Log.d(TAG_LOG, "DETAIL DATA: " + movieDetail.toString());
                } catch (JSONException e) {
                    Log.e(TAG_LOG, "ERROR:" + e.getMessage(), e);
                    e.printStackTrace();
                }
            } catch (IOException e) {
                Log.i(TAG_LOG, "ERROR : " + e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                if(bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(TAG_LOG, "ERROR: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            return movieDetail;
        }

        @Override
        protected void onPostExecute(MovieDetail detail) {
            super.onPostExecute(detail);
            Picasso.with(getActivity()).load(Constant.THUMBNAIL_BASE_URL + detail.getPosterPath()).into(posterImageView);
            movieTitleView.setText(detail.getTitle());
            runtimeView.setText(Integer.toString(detail.getRunTime()) + "min");
            manufacturerYearView.setText(MovieUtils.getReadableManufacturerYear(detail.getReleaseDate()));
            voteAverageView.setText(detail.getVoteAverage() + "/10");
            overviewTextView.setText(detail.getOverview());
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
