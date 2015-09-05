package com.udacity.hoanv.popularmovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoaNV on 9/4/15.
 */
public class MovieUtils {

    public static List<MovieThumbnail> getThumbnailFromJson(String movieJsonStr) throws JSONException {
        if(movieJsonStr == null || movieJsonStr.isEmpty()){
            return null;
        }

        JSONObject rootObject = new JSONObject(movieJsonStr);
        JSONArray resultList = rootObject.getJSONArray(Constant.OWN_RESULT);
        List<MovieThumbnail> listMovie = new ArrayList<>();

        for (int i = 0; i < resultList.length(); i++) {
            JSONObject movieJsonObj = resultList.getJSONObject(i);
            Long id = movieJsonObj.getLong(Constant.OWN_ID);
            String thumbnailURL = movieJsonObj.getString(Constant.OWN_THUMBNAIL);
            listMovie.add(new MovieThumbnail(id, thumbnailURL));
        }
        return listMovie;
    }

    public static MovieDetail getDeailFromJson(String movieDetailJsonStr) throws JSONException {
        if(movieDetailJsonStr == null || movieDetailJsonStr.isEmpty()){
            return null;
        }

        MovieDetail movieDetail = new MovieDetail();
        JSONObject rootObj = new JSONObject(movieDetailJsonStr);
        movieDetail.setOverview(rootObj.getString(Constant.OWN_OVERVIEW));
        movieDetail.setPosterPath(rootObj.getString(Constant.OWN_POSTER_PATH));
        movieDetail.setRunTime(rootObj.getInt(Constant.OWN_RUNTIME));
        movieDetail.setReleaseDate(rootObj.getString(Constant.OWN_RELEASE_DATE));
        movieDetail.setTitle(rootObj.getString(Constant.OWN_TITLE));
        movieDetail.setVoteAverage(rootObj.getDouble(Constant.OWN_VOTE_AVERAGE));
        return movieDetail;
    }

    public static String getReadableManufacturerYear(String year) {
        if (year != null && !year.isEmpty()) {
            return year.split("-")[0];
        } else {
            return null;
        }
    }

}
