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

    private static final String OWN_RESULT = "results";
    private static final String OWN_ID = "id";
    private static final String OWN_THUMBNAIL = "poster_path";

    public static List<MovieThumbnail> getThumbnailFromJson(String movieJsonStr) throws JSONException {
        if(movieJsonStr == null || movieJsonStr.isEmpty()){
            return null;
        }

        JSONObject rootObject = new JSONObject(movieJsonStr);
        JSONArray resultList = rootObject.getJSONArray(OWN_RESULT);
        List<MovieThumbnail> listMovie = new ArrayList<>();

        for (int i = 0; i < resultList.length(); i++) {
            JSONObject movieJsonObj = resultList.getJSONObject(i);
            Long id = movieJsonObj.getLong(OWN_ID);
            String thumbnailURL = movieJsonObj.getString(OWN_THUMBNAIL);
            listMovie.add(new MovieThumbnail(id, thumbnailURL));
        }
        return listMovie;
    }


}
