package com.harshssd.yuppdate.data;

import com.harshssd.yuppdate.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the conversion from json returned from MovieDB API to the Movie Objects.
 *
 * @author Harsha
 */
public class MovieDataParser {

    public List<Movie> getMovieObjectFromJson(String moviesJsonStr) throws JSONException {

        List<Movie> movieObjects = new ArrayList<>();

        final String TMDB_MOVIE_LIST = "results";

        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray movieJsonArray = moviesJson.getJSONArray(TMDB_MOVIE_LIST);

        for (int i = 0; i < movieJsonArray.length(); i++) {
            movieObjects.add(createMovieObjectFromJson(movieJsonArray.getJSONObject(i)));
        }
        return movieObjects;
    }

    private Movie createMovieObjectFromJson(JSONObject movieJsonObject) throws JSONException {
        final String TMDB_MOVIE_ID = "id";
        final String TMDB_MOVIE_TITLE = "original_title";
        final String TMDB_MOVIE_POSTER_PATH = "poster_path";
        final String TMDB_MOVIE_RELEASE_DATE = "release_date";
        final String TMDB_MOVIE_OVERVIEW = "overview";
        final String TMDB_MOVIE_AVERAGE_RATING = "vote_average";

        Movie movie = Movie.builder()
                .id(movieJsonObject.getString(TMDB_MOVIE_ID))
                .title(movieJsonObject.getString(TMDB_MOVIE_TITLE))
                .overView(movieJsonObject.getString(TMDB_MOVIE_OVERVIEW))
                .releaseDate(Date.valueOf(movieJsonObject.getString(TMDB_MOVIE_RELEASE_DATE)))
                .posterPath(movieJsonObject.getString(TMDB_MOVIE_POSTER_PATH))
                .averageRating(Float.valueOf(movieJsonObject.getString(TMDB_MOVIE_AVERAGE_RATING)))
                .build();
        return movie;
    }
}
