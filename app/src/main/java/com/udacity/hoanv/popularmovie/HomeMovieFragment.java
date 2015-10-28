package com.udacity.hoanv.popularmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.udacity.hoanv.popularmovie.Entity.DiscoverMovie;
import com.udacity.hoanv.popularmovie.Entity.DiscoverMovieResult;
import com.udacity.hoanv.popularmovie.service.MovieDBService;
import com.udacity.hoanv.popularmovie.service.WebService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.Call;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeMovieFragment extends Fragment implements OnAsyncTaskCompleted {

    private static final String TAG_LOG = HomeMovieFragment.class.getSimpleName();
    private ImageAdapter imageAdapter;
    private DialogFragment dialogFragment;
    @Bind(R.id.movie_gridview) private GridView gridView;

    public HomeMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG_LOG, "HomeMovieFragment onCreateView");
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        if(fragment != null) {
            imageAdapter = new ImageAdapter(getActivity(), new ArrayList<DiscoverMovie>());
            gridView.setAdapter(imageAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                    detailIntent.putExtra(Constant.EXTRA_MOVIEID, imageAdapter.getItemId(position));
                    startActivity(detailIntent);
                }

            });
        }
        return fragment;
    }

    @Override
    public void onStart() {
        Log.d(TAG_LOG, "HomeMovieFragment onStart");
        super.onStart();
        updateScreen();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void updateScreen() {
        Log.d(TAG_LOG, "HomeMovieFragment updateScreen");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortByValue = sharedPreferences.getString(getString(R.string.pref_sort_by_key), getString(R.string.pref_sort_by_popular));
        MoviePopularTask popularTask = new MoviePopularTask(this);
        popularTask.execute(sortByValue);
    }

    @Override
    public void onTaskCompleted(List<DiscoverMovie> result) {
        Log.d(TAG_LOG, "HomeMovieFragment onTaskCompleted");
        imageAdapter.clear();
        imageAdapter.setMovieThumbnailList(result);
    }

    public class MoviePopularTask extends AsyncTask<String, Void, List<DiscoverMovie>>{

        private final String TAG_LOG = MoviePopularTask.class.getSimpleName();
        private OnAsyncTaskCompleted taskListener;

        public MoviePopularTask(OnAsyncTaskCompleted taskListener) {
            this.taskListener = taskListener;
        }

        @Override
        protected List<DiscoverMovie> doInBackground(String... params) {
            if(params == null || params.length == 0){
                Log.i(TAG_LOG, "MoviePopularTask has been null params");
                return null;
            }

            dialogFragment = ProgressDialogFragment.getInstance();
            dialogFragment.show(getFragmentManager(), "Loading");

            //Call API from TheMovieDB by Retrofit Library
            MovieDBService movieDBService = WebService.getMovieDBService();
            Call<DiscoverMovieResult> call = movieDBService.listMovieThumbnail(getString(R.string.api_key), params[0]);

            try {
                Log.d(TAG_LOG, "CALL API with param : " + params[0]);
                return call.execute().body().getResults();
            } catch (IOException e) {
                Log.e(TAG_LOG, "ERROR", e);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<DiscoverMovie> result) {
            Log.d(TAG_LOG, "MoviePopularTask onPostExecute");
            super.onPostExecute(result);
            if(dialogFragment != null){
                dialogFragment.dismiss();
            }
            if (imageAdapter != null) {
                taskListener.onTaskCompleted(result);
            }
        }
    }

}
