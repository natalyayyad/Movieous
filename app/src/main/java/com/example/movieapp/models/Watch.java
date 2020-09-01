package com.example.movieapp.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Watch {
    private LocalDate date;
    private LocalTime time;
    private Movie watchMovie;

    public Watch() {
    }

    public Watch(LocalDate date, LocalTime time, Movie watchMovie) {
        this.date = date;
        this.time = time;
        this.watchMovie = watchMovie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Movie getWatchMovie() {
        return watchMovie;
    }

    public void setWatchMovie(Movie watchMovie) {
        this.watchMovie = watchMovie;
    }

    @Override
    public String toString() {
        return "Watch{" +
                "date=" + date +
                ", time=" + time +
                ", watchMovie=" + watchMovie +
                '}';
    }
    
}
