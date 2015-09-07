package com.udacity.hoanv.popularmovie;

/**
 * Created by HoaNV on 9/4/15.
 */
public class MovieUtils {

    public static String getReadableManufacturerYear(String year) {
        if (year != null && !year.isEmpty()) {
            return year.split("-")[0];
        } else {
            return null;
        }
    }

}
