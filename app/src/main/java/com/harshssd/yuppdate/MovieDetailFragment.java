package com.harshssd.yuppdate;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "movie_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Movie mMovieItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mMovieItem = getArguments().getParcelable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mMovieItem.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mMovieItem != null) {
            ImageView mPosterView = ((ImageView) rootView.findViewById(R.id.detail_movie_poster_image));
            final String POSTER_URL = "http://image.tmdb.org/t/p/w185/" + mMovieItem.getPosterPath();
            Picasso.with(rootView.getContext())
                    .load(POSTER_URL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(mPosterView);
            TextView mReleaseYear = (TextView) rootView.findViewById(R.id.detail_movie_release_year_text);
            mReleaseYear.setText(String.valueOf(mMovieItem.getReleaseDate().getYear() + 1900));
            TextView mAverageRating = (TextView) rootView.findViewById(R.id.detail_movie_rating_text);
            mAverageRating.setText(mMovieItem.getAverageRating() + "/10");
            TextView mOverview = (TextView) rootView.findViewById(R.id.detail_movie_overview);
            mOverview.setText(mMovieItem.getOverView());
        }

        return rootView;
    }
}
