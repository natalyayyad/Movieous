package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.models.Movie;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width,(int) (height*0.3));

        final Button rateButton = findViewById(R.id.popup_submit);
        final RatingBar ratingBar = findViewById(R.id.popup_ratingBar);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("ID");
        ArrayList<Movie> movies = MainActivity.Movies;
        Movie ratedMovie = new Movie();
        int i;
        AtomicReference<Float> rate = new AtomicReference<>((float) 0);
        for(i=0; i<movies.size(); i++){
            if (movies.get(i).getId().equals(id))
                ratedMovie = movies.get(i);
        }
        DataBaseHelper mydb = new DataBaseHelper(PopUpActivity.this,"RATEDLIST",null,4);
        if(mydb.checkifExists("RATEDLIST",HomeActivity.shared,id)){
            ratingBar.setRating(Float.parseFloat(mydb.getRate(HomeActivity.shared,id)));
        }


        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            rate.set(ratingBar.getRating());
        });

        Movie finalRatedMovie = ratedMovie;
        rateButton.setOnClickListener(view -> {
            if(mydb.checkifExists("RATEDLIST",HomeActivity.shared,id)){
                mydb.updateRate(HomeActivity.shared,id,ratingBar.getRating() + "");
                Toast.makeText(PopUpActivity.this, "Updated Rate",Toast.LENGTH_LONG).show();
            }

            else{
                mydb.insertRatedMovie(HomeActivity.shared,id, finalRatedMovie.getTitle(),ratingBar.getRating() + "" , finalRatedMovie.getPosterurl());
                Toast.makeText(PopUpActivity.this, "Successfully Inserted Rate to DB",Toast.LENGTH_LONG).show();
            }

            finish();
        });
    }
}