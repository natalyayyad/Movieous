
package com.example.movieapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("storyline")
    @Expose
    private String storyline;
    @SerializedName("actors")
    @Expose
    private List<String> actors = null;
    @SerializedName("imdbRating")
    @Expose
    private Double imdbRating;
    @SerializedName("posterurl")
    @Expose
    private String posterurl;
   // private final static long serialVersionUID = -3399833641372580363L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                ", duration='" + duration + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", storyline='" + storyline + '\'' +
                ", actors=" + actors +
                ", imdbRating=" + imdbRating +
                ", posterurl='" + posterurl + '\'' +
                '}';
    }

    /**
     *
     * @param duration
     * @param actors
     * @param posterurl
     * @param year
     * @param releaseDate
     * @param genres
     * @param storyline
     * @param imdbRating
     * @param id
     * @param title
     */
    public Movie(String id, String title, String year, List<String> genres, String duration, String releaseDate, String storyline, List<String> actors, Double imdbRating, String posterurl) {
        super();
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.storyline = storyline;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.posterurl = posterurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

}
