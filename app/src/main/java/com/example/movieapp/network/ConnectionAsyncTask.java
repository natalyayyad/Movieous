package com.example.movieapp.network;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.movieapp.activities.HomeActivity;
import com.example.movieapp.activities.MainActivity;
import com.example.movieapp.models.Movie;

import java.util.List;


public class ConnectionAsyncTask extends AsyncTask<String, String, String> {

    Activity activity;

    public ConnectionAsyncTask(Activity activity) {

        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

        ((MainActivity) activity).setButtonText("connecting");
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {

        String data = HttpManager.getData(params[0]);

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((MainActivity) activity).setProgress(false);
        ((MainActivity) activity).setButtonText("connected");
        List<Movie> movies = MovieJasonParser.getObjectFromJason(s);
        ((MainActivity)activity).setMovies(movies);

    }
}
