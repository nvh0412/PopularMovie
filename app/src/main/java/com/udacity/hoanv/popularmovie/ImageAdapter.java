package com.udacity.hoanv.popularmovie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HoaNV on 9/4/15.
 */
public class ImageAdapter extends BaseAdapter {

    private static final String TAG_LOG = ImageAdapter.class.getSimpleName();
    private List<MovieThumbnail> movieThumbnailList;
    private Context mContext;
    private View parentView;

    public ImageAdapter(View parentView,Context mContext,List<MovieThumbnail> movieThumbnailList) {
        this.movieThumbnailList = movieThumbnailList;
        this.mContext = mContext;
        this.parentView = parentView;
    }

    public void clear(){
        movieThumbnailList.clear();
        notifyDataSetChanged();
    }

    public void setMovieThumbnailList(List<MovieThumbnail> movieThumbnailList) {
        this.movieThumbnailList = movieThumbnailList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieThumbnailList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieThumbnailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieThumbnailList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If it isn't recycled, initialize some attributes.
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    parentView.getWidth() / 2,
                    parentView.getHeight() / 2));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        MovieThumbnail item = movieThumbnailList.get(position);
        Picasso.with(mContext).load(Constant.THUMBNAIL_BASE_URL + item.getUrlThumbnail())
                .into(imageView);
        return imageView;
    }
}
