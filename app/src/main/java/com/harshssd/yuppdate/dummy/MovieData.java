package com.harshssd.yuppdate.dummy;

import com.harshssd.yuppdate.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harsha on 12/10/16.
 */
public class MovieData {
    public static List<Movie> MOVIES;
    public static Map<String, Movie> MOVIE_MAP;

    static {
        // Add some Data to the Movie List.
        MOVIES = new ArrayList<>();
        MOVIES.add(new Movie("1234", "Rang De Basanthi", "/url_to_rang_de_basanthi"));
        MOVIES.add(new Movie("4232", "Dil Chahta Hain", "/url_to_dil_chahta_hain"));
        MOVIES.add(new Movie("5452", "Maqbool", "/url_to_maqbool"));
        MOVIES.add(new Movie("7444", "Gulaal", "/url_to_gulaal"));
        MOVIES.add(new Movie("6564", "Swades", "/url_to_swades"));
        MOVIES.add(new Movie("5432", "Rock On", "/url_to_rock_on"));
        MOVIES.add(new Movie("5432", "Gangs of Wasseypur", "/url_to_gangs-of_wasseypur"));
        createMovieMap();
    }

    private static void createMovieMap() {
        MOVIE_MAP = new HashMap<>();
        for(Movie movie: MOVIES) {
            MOVIE_MAP.put(movie.getId(), movie);
        }
    }

}
