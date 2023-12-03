package com.dongyang.moviewreviewweb.moviereviewer.movie.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private String director;
    private int releaseDate;
    private String posterLink;
}
