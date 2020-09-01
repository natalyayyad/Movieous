package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.Watch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView moviePosterurl;
    TextView movieTitle;
    TextView movieCategory;
    TextView movieActors;
    TextView movieYear, movieDuration, movieReleaseDate;
    TextView movieStoryline, usersComments;
    EditText movieComment;
    Button commentButton, watchListButton, ratedListButton;

   // public static ArrayList<Movie>watchList = new ArrayList<>();;
    public static ArrayList<Movie> ratedList =new ArrayList<>();
    public static ArrayList<Watch> watchList = new ArrayList<>();
    //public static Set<Movie>watchList ;
    //public static Set<Movie> ratedList;
    int i=0, k =0;
    public  static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        moviePosterurl  = findViewById(R.id.movie_posterurl);
        movieTitle = findViewById(R.id.movie_title);
        movieCategory = findViewById(R.id.movie_genres);
        movieActors = findViewById(R.id.movie_actors);
        movieYear = findViewById(R.id.movie_year);
        movieDuration = findViewById(R.id.movie_duration);
        movieReleaseDate = findViewById(R.id.movie_releaseDate);
        movieStoryline = findViewById(R.id.movie_storyline);
        movieComment = findViewById(R.id.movieDetails_commentEdit);
        usersComments = findViewById(R.id.movieDetails_UserCommentEdit);
        watchListButton = findViewById(R.id.watchListButton);
        ratedListButton = findViewById(R.id.ratedListButton);
        commentButton = findViewById(R.id.movieDetails_commentButton);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("ID");
        ArrayList<Movie> movies = MainActivity.Movies;
        Movie toDB = new Movie();
        DataBaseHelper mydb = new DataBaseHelper(MovieDetailsActivity.this,"WATCHLIST",null,4);
        DataBaseHelper mydb2 = new DataBaseHelper(MovieDetailsActivity.this,"RATEDLIST",null,4);
        DataBaseHelper mydb3 = new DataBaseHelper(MovieDetailsActivity.this, "Comment", null, 4);
        for (i=0; i<movies.size(); i++){
            if (movies.get(i).getId().equals(id)){
                toDB = movies.get(i);
                Picasso.with(getBaseContext()).load(movies.get(i).getPosterurl()).fit().centerCrop().into(moviePosterurl);
                movieTitle.setText(movies.get(i).getTitle());
                movieYear.setText("Year: " + movies.get(i).getYear());
                movieDuration.setText("Duration: "+ movies.get(i).getDuration());
                movieReleaseDate.setText("Release Date: " + movies.get(i).getReleaseDate());
                movieStoryline.setText(movies.get(i).getStoryline());
                String temp = "";
                for (k=0;k<movies.get(i).getGenres().size();k++)
                    temp = temp + movies.get(i).getGenres().get(k) + ", ";
                temp = charRemoveAt(temp, temp.length()-2);
                movieCategory.setText(temp);
                temp = "";
                for (k=0;k<movies.get(i).getActors().size();k++)
                    temp = temp + movies.get(i).getActors().get(k) + ", ";
                temp = charRemoveAt(temp, temp.length()-2);
                movieActors.setText(temp);
                temp = "";

                if(mydb.checkifExists("WATCHLIST",HomeActivity.shared,movies.get(i).getId()))
                    watchListButton.setText("Remove From Watch List");
                else
                    watchListButton.setText("Add to Watch List");


                if(mydb2.checkifExists("RATEDLIST",HomeActivity.shared,movies.get(i).getId()))
                    ratedListButton.setText("RATED");
                else
                    ratedListButton.setText("RATE");

            }
            else
                Toast.makeText(MovieDetailsActivity.this, "Sth went wrong...",Toast.LENGTH_LONG).show();
        }

        Movie finalToDB = toDB;
        Cursor c = mydb3.getComments(id);
        while (c.moveToNext()) {
            usersComments.append(c.getString(3).toUpperCase() + " : " + c.getString(2)+"\n");
        }

        commentButton.setOnClickListener(view -> {
            if(!movieComment.getText().toString().isEmpty()){
                DataBaseHelper mydb4 = new DataBaseHelper(MovieDetailsActivity.this, "Comment", null, 4);
                //String name = mydb4.getUserName(HomeActivity.shared);
                mydb4.insertComment(HomeActivity.shared, id, HomeActivity.name, movieComment.getText().toString());
                Cursor cursor = mydb4.getComments(id);
                if (cursor.getCount()!=0){
                    while (cursor.moveToNext()) {
                        usersComments.append( cursor.getString(3).toUpperCase() + " : " + cursor.getString(2)+"\n");
                    }
                }
                movieComment.setText("");
            }
        });

        watchListButton.setOnClickListener(view -> {
            if(watchListButton.getText().equals("Remove From Watch List")){
                watchListButton.setText("Add to Watch List");
                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext(),"WATCHLIST",null,4);
                dataBaseHelper.removeWatchMovie(HomeActivity.shared, finalToDB.getId());
                Toast.makeText(MovieDetailsActivity.this, "Removed From Watch List",Toast.LENGTH_LONG).show();
            }
            else{
                watchListButton.setText("Remove From Watch List");
                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext(),"WATCHLIST",null,4);
                dataBaseHelper.insertWatchMovie(HomeActivity.shared, finalToDB.getId(), finalToDB.getTitle(), finalToDB.getPosterurl());
                Toast.makeText(MovieDetailsActivity.this, "Sucessfuly inserted to DB",Toast.LENGTH_LONG).show();
            }
        });

        ratedListButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(view.getContext(), PopUpActivity.class);
            intent1.putExtra("ID", id);
            (view.getContext()).startActivity(intent1);
        });

        /*
        if (flag == false)
            watchListButton.setText("Add to Watch List");
        else
            watchListButton.setText("Remove from Watch List");
        // Buttons
        watchListButton.setOnClickListener(view -> {
            if (watchListButton.getText().equals("Add to Watch List")){
                flag = true;
                watchListButton.setText("Remove from Watch List");
                for (i=0; i<movies.size(); i++) {
                    if (movies.get(i).getId().equals(id)){
                        Watch watch = new Watch();
                        LocalDate nowDate = LocalDate.now(); // Create a date object
                        LocalTime nowTime = LocalTime.now(TimeZone.getTimeZone("Asia/Jerusalem").toZoneId());
                        watch.setDate(nowDate);
                        watch.setTime(nowTime);
                        watch.setWatchMovie(movies.get(i));
                        watchList.add(watch);
                        break;
                    }
                }
            }
            else{
                flag = false;
                watchListButton.setText("Add to Watch List");
               for(i=0;i<watchList.size();i++){
                   if(watchList.get(i).getWatchMovie().getId().equals(id))
                       watchList.remove(i);
               }
            }
        });

        ratedListButton.setOnClickListener(view -> {

            ratedList.add(movies.get(i));
        });

        //TODO: implement Comment Section

         */
    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}