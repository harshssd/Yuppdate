package com.harshssd.yuppdate;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by Harsha on 12/9/16.
 */
@Data
@Builder
public class Movie implements Parcelable {
    private String id;
    private String title;
    private String posterPath;
    private String overView;
    private Date releaseDate;
    private float averageRating;

    public static SimpleDateFormat MOVIE_RELEASE_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

    public Movie(String id, String title, String posterPath, String overView, Date releaseDate,
                 float averageRating) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }

    private Movie(Parcel parcel) {
        id = parcel.readString();
        title = parcel.readString();
        posterPath = parcel.readString();
        overView = parcel.readString();
        releaseDate = new Date(parcel.readString());
        averageRating = parcel.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overView);
        dest.writeString(releaseDate.toString());
        dest.writeFloat(averageRating);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
