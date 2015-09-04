package com.udacity.hoanv.popularmovie;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

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

    private static final String TAG_LOG = MainActivityFragment.class.getSimpleName();
    private ImageAdapter imageAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        if(fragment != null) {
            GridView gridView = (GridView) fragment.findViewById(R.id.movie_gridview);
            imageAdapter = new ImageAdapter(gridView, getActivity(), new ArrayList<MovieThumbnail>());
            gridView.setAdapter(imageAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                    startActivity(detailIntent);
                }

            });
            //run task for get result from API
            new MoviePopularTask().execute("popularity.desc");
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public class MoviePopularTask extends AsyncTask<String, Void, List<MovieThumbnail>>{

        private final String TAG_LOG = MoviePopularTask.class.getSimpleName();

        @Override
        protected List<MovieThumbnail> doInBackground(String... params) {
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

            try {
                return MovieUtils.getThumbnailFromJson(movieJsonStr);
            } catch (JSONException e) {
                Log.e(TAG_LOG, "ERROR: " + e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<MovieThumbnail> result) {
            super.onPostExecute(result);
            if (imageAdapter != null) {
                imageAdapter.clear();
                imageAdapter.setMovieThumbnailList(result);
            }
        }
    }

}
