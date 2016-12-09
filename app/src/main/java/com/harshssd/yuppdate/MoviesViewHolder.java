package com.harshssd.yuppdate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.harshssd.yuppdate.dummy.DummyContent;

/**
 * Created by Harsha on 12/8/16.
 */
public class MoviesViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public DummyContent.DummyItem mItem;

    public MoviesViewHolder(View view) {
        super(view);
        mView = view;
        mIdView = (TextView) view.findViewById(R.id.id);
        mContentView = (TextView) view.findViewById(R.id.content);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
