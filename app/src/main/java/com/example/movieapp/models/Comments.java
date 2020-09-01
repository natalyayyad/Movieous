package com.example.movieapp.models;

import java.util.ArrayList;

public class Comments {
    private String email;
    private String id;
    private ArrayList<String> comments;

    public Comments() {
    }

    public Comments(String email, String id, ArrayList<String> comments) {
        this.email = email;
        this.id = id;
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", comments=" + comments +
                '}';
    }
}
