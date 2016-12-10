package com.harshssd.yuppdate;

/**
 * Created by Harsha on 12/9/16.
 */
public class Movie {
    private String id;
    private String title;
    private String posterPath;

    public Movie(String id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
