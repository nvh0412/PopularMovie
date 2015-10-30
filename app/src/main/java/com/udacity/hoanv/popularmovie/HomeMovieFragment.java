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

import com.udacity.hoanv.popularmovie.Entity.PosterMovieList;
import com.udacity.hoanv.popularmovie.service.MovieDBService;
import com.udacity.hoanv.popularmovie.service.WebService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeMovieFragment extends Fragment {

    private static final String TAG_LOG = HomeMovieFragment.class.getSimpleName();
    private ImageAdapter imageAdapter;
    private DialogFragment dialogFragment;
    @Bind(R.id.movie_gridview) GridView gridView;

    public HomeMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG_LOG, "HomeMovieFragment onCreateView");
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        if(fragment != null) {
            ButterKnife.bind(this, fragment);
            //imageAdapter = new ImageAdapter(getActivity(), new ArrayList<DiscoverMovie>());
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
        MoviePopularTask popularTask = new MoviePopularTask();
        popularTask.execute(sortByValue);
    }

    public class MoviePopularTask extends AsyncTask<String, Void, Void>{

        private final String TAG_LOG = MoviePopularTask.class.getSimpleName();

        @Override
        protected Void doInBackground(String... params) {
            if(params == null || params.length == 0){
                Log.i(TAG_LOG, "MoviePopularTask has been null params");
                return null;
            }

            dialogFragment = ProgressDialogFragment.getInstance();
            dialogFragment.show(getFragmentManager(), "Loading");

            //Call API from TheMovieDB by Retrofit Library
            MovieDBService movieDBService = WebService.getMovieDBService();
            Call<PosterMovieList> call = movieDBService.listMovieThumbnail(getString(R.string.api_key), params[0]);

            Log.d(TAG_LOG, "CALL API with param : " + params[0]);
            return null; //call.execute().body().getResults();
        }

//        @Override
//        protected void onPostExecute(List<PosterMovie> result) {
//            Log.d(TAG_LOG, "MoviePopularTask onPostExecute");
//            super.onPostExecute(result);
//            if(dialogFragment != null){
//                dialogFragment.dismiss();
//            }
//            if (imageAdapter != null) {
//                //taskListener.onTaskCompleted(result);
//            }
//        }
    }

}
