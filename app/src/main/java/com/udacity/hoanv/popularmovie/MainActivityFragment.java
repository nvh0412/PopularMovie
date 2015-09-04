package com.udacity.hoanv.popularmovie;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<MovieThumbnail> movieList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new MoviePopularTask().execute("popularity.desc");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public class MoviePopularTask extends AsyncTask<String, Void, String>{

        private final String TAG_LOG = MoviePopularTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            String movieJsonStr = null;
            HttpURLConnection conn = null;
            URL urlConn = null;
            BufferedReader bufferedReader = null;

            final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String API_KEY = "api_key";
            final String OWN_API_KEY = "38a73981d5e8bc34978f3a9d569fe223";
            final String SORT_BY = "sort_by";

            try {
                //Build URL Path by Uri Builder
                //Set API_KEY and sort_by parameter.
                Uri uriBuilder = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(API_KEY, OWN_API_KEY)
                        .appendQueryParameter(SORT_BY, params[0])
                        .build();

                //Initialize url
                urlConn = new URL(uriBuilder.toString());

                //Open connection to MovieAPI.
                conn = (HttpURLConnection) urlConn.openConnection();

                //Get buffer from result which has been returned by call api
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Read data
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                movieJsonStr = buffer.toString();
                Log.i(TAG_LOG, "Data: " + movieJsonStr);
            } catch (IOException e) {
                Log.e(TAG_LOG, "ERROR: " + e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                if(bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(TAG_LOG, "ERROR: " + e.getMessage(), e);
                        e.printStackTrace();
                    }
                }
            }

            return movieJsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            movieList = new ArrayList<>();

        }
    }

}
