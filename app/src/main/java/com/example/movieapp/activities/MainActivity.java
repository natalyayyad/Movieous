package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.models.Movie;
import com.example.movieapp.network.ConnectionAsyncTask;
import com.example.movieapp.network.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button connectButton;
    SharedPrefManager sharedPrefManager;
    public static ArrayList<Movie>Movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        connectButton = (Button)findViewById(R.id.connectButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectButton.setEnabled(false);
                try {
                    ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
                    connectionAsyncTask.execute("https://firebasestorage.googleapis.com/v0/b/advance-proj1.appspot.com/o/movies-in-theaters.json?alt=media&token=e3121ae7-be1b-4480-99d8-c1d0ad2eaa2f");

                    if(!sharedPrefManager.readString("email","noValue").equals("noValue") &&
                            !sharedPrefManager.readString("password","noValue").equals("noValue")){
                        Toast.makeText(MainActivity.this, sharedPrefManager.readString("email","noValue") + " is already logged in",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("email",sharedPrefManager.readString("email","noValue"));
                        MainActivity.this.startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        MainActivity.this.startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "CONNECTION FAILED",Toast.LENGTH_LONG).show();
                    connectButton.setEnabled(true);
                    e.printStackTrace();
                }

            }
        });
    }
    public void setButtonText(String text) {
        connectButton.setText(text);
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }


    public void setMovies (List<Movie> movies){
        Movies = new ArrayList<>();
        Movies.addAll(movies);
    }
}