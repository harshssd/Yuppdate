package com.harshssd.yuppdate.data;

import android.net.Uri;
import android.os.AsyncTask;
import com.harshssd.yuppdate.BuildConfig;
import android.util.Log;

import com.harshssd.yuppdate.Movie;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

/**
 * Asynchronous Task to fetch the Popular Movies using MovieDB API.
 * https://www.themoviedb.org/documentation/api
 *
 * @author Harsha
 */
public class FetchPopularMoviesTask extends AsyncTask<String, Void, List<Movie>> {

    private final String LOG_TAG = FetchPopularMoviesTask.class.getSimpleName();

    @Override
    protected List<Movie> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            final String MOVIE_DB_BASE_URL = params[0];
            final String API_KEY = "api_key";
            Uri movieDbUri = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, BuildConfig.THE_MOVIE_DB_API_KEY)
                    .build();
            URL movieDbUrl = new URL(movieDbUri.toString());

            // Create the request to MovieDB API, and open the connection
            urlConnection = (HttpURLConnection) movieDbUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            MovieDataParser movieDataParser = new MovieDataParser();
            return movieDataParser.getMovieObjectFromJson(buffer.toString());
        } catch (IOException | JSONException | ParseException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
    }
}
