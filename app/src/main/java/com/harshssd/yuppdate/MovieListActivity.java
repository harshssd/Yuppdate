package com.harshssd.yuppdate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.harshssd.yuppdate.data.FetchPopularMoviesTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private List<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // The detail container view will be present only in the
        // large-screen layouts (res/values-w900dp).
        // If this view is present, then the
        // activity should be in two-pane mode.
        mTwoPane = findViewById(R.id.movie_detail_container) != null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sortKey = sharedPrefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_key_popularity));
        String TMDBUrl = "https://api.themoviedb.org/3/movie/popular?";
        if (sortKey.equals(getString(R.string.pref_sort_key_rating))) {
            TMDBUrl = "https://api.themoviedb.org/3/movie/top_rated?";
        }

        FetchPopularMoviesTask fetchPopularMoviesTask = new FetchPopularMoviesTask();
        try {
            mMovies = fetchPopularMoviesTask.execute(TMDBUrl).get();
        } catch (InterruptedException | ExecutionException e) {
            mMovies = null;
            e.printStackTrace();
        }
        recyclerView.setAdapter(new MoviesAdapter(mMovies));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
