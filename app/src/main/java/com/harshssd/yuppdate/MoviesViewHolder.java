package com.harshssd.yuppdate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Harsha on 12/8/16.
 */
public class MoviesViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mTitleView;
    public final ImageView mPosterView;
    public Movie mMovieItem;

    public MoviesViewHolder(View view) {
        super(view);
        mView = view;
        mTitleView = (TextView) view.findViewById(R.id.title);
        mPosterView = (ImageView) view.findViewById(R.id.moviePoster);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mTitleView.getText() + "'";
    }
}
