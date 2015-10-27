package com.udacity.hoanv.popularmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.hoanv.popularmovie.Entity.DiscoverMovie;

import java.util.List;

/**
 * Created by HoaNV on 9/4/15.
 */
public class ImageAdapter extends BaseAdapter {

    private static final String TAG_LOG = ImageAdapter.class.getSimpleName();
    private List<DiscoverMovie> discoverMovieList;
    private Context mContext;

    public ImageAdapter(Context mContext,List<DiscoverMovie> discoverMovieList) {
        this.discoverMovieList = discoverMovieList;
        this.mContext = mContext;
    }

    public void clear(){
        discoverMovieList.clear();
        notifyDataSetChanged();
    }

    public void setMovieThumbnailList(List<DiscoverMovie> discoverMovieList) {
        this.discoverMovieList = discoverMovieList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return discoverMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return discoverMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return discoverMovieList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If it isn't recycled, initialize some attributes.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageView = (ImageView) inflater.inflate(R.layout.thumbnail_movie, null);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            imageView = (ImageView) convertView;
        }

        DiscoverMovie item = discoverMovieList.get(position);
        Picasso.with(mContext).load(Constant.THUMBNAIL_BASE_URL + item.getPosterPath())
                .into(imageView);
        return imageView;
    }

}
