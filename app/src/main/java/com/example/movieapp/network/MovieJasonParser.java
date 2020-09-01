package com.example.movieapp.network;

import com.example.movieapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieJasonParser {


    public static List<Movie> getObjectFromJason(String jason) {
        List<Movie> movies;
        ArrayList<String>empty;

        try {
            JSONArray jsonArray = new JSONArray(jason);
            movies = new ArrayList<>();
            empty = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Movie movie = new Movie();
                movie.setId(jsonObject.getString("id"));
                if (jsonObject.getString("title").length() !=0)
                     movie.setTitle(jsonObject.getString("title"));
                else
                    movie.setTitle("No title");

                if (jsonObject.getString("year").length() !=0)
                     movie.setYear(jsonObject.getString("year"));
                else
                    movie.setYear("unknown");
                JSONArray genres =  jsonObject.getJSONArray("genres");
                if(genres.length() > 0){
                    ArrayList<String>temp = new ArrayList<>();
                    for (int k = 0; k < genres.length(); k++)
                        temp.add(k,genres.getString(k));
                    movie.setGenres(temp);
                }
                else
                    movie.setGenres(empty);
                if (jsonObject.getString("duration").length() !=0)
                     movie.setDuration(jsonObject.getString("duration"));
                else
                    movie.setDuration("unknown");

                if (jsonObject.getString("releaseDate").length() !=0)
                    movie.setReleaseDate(jsonObject.getString("releaseDate"));
                else
                    movie.setReleaseDate("unknown");

                if (jsonObject.getString("storyline").length() !=0)
                    movie.setStoryline(jsonObject.getString("storyline"));
                else
                    movie.setStoryline("no storyline available");
                JSONArray actors =  jsonObject.getJSONArray("actors");
                if(actors.length() > 0){
                    ArrayList<String>temp = new ArrayList<>();
                    for (int k = 0; k < actors.length(); k++)
                        temp.add(k,actors.getString(k));
                    movie.setActors(temp);
                }
                else
                    movie.setActors(empty);

                if (jsonObject.getString("imdbRating").length() != 0 )
                    movie.setImdbRating(jsonObject.getDouble("imdbRating"));
                else
                    movie.setImdbRating(0.0);

                movie.setPosterurl(jsonObject.getString("posterurl"));
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movies;
    }
}
