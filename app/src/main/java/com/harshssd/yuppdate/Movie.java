package com.harshssd.yuppdate;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by Harsha on 12/9/16.
 */
@Data
@Builder
public class Movie implements Serializable {
    private String id;
    private String title;
    private String posterPath;
    private String overView;
    private Date releaseDate;
    private float averageRating;
}
