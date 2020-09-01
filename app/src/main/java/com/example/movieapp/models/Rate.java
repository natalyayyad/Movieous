package com.example.movieapp.models;

public class Rate {
    String id;
    float value;

    public Rate() {
    }

    public Rate(String id, float value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
