package com.udacity.hoanv.popularmovie;

/**
 * Created by HoaNV on 9/4/15.
 */
public class MovieThumbnail {

    private Long id;
    private String urlThumbnail;

    public MovieThumbnail() {
    }

    public MovieThumbnail(Long id, String urlThumbnail) {
        this.id = id;
        this.urlThumbnail = urlThumbnail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    @Override
    public String toString() {
        return "MovieThumbnail{" +
                "id=" + id +
                ", urlThumbnail='" + urlThumbnail + '\'' +
                '}';
    }
}
