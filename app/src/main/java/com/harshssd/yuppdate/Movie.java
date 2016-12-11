package com.harshssd.yuppdate;

import java.io.Serializable;

/**
 * Created by Harsha on 12/9/16.
 */
public class Movie implements Serializable {
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
