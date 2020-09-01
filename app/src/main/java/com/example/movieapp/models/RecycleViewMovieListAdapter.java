package com.example.movieapp.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.activities.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecycleViewMovieListAdapter extends RecyclerView.Adapter<RecycleViewMovieListAdapter.MyViewHolder> implements Filterable {


    private Context mContext;
    private List<Movie> mData;
    private List<Movie> movieList;

    public RecycleViewMovieListAdapter(Context mContext, List<Movie> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.movieList = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movie_title.setText(mData.get(position).getTitle());
        Picasso.with(mContext).load(mData.get(position).getPosterurl()).fit().centerCrop().into(holder.movie_poster);

        holder.movie_cardView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra("ID", mData.get(position).getId());
            mContext.startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        // run on background thread
        @NotNull
        @Override
        protected FilterResults performFiltering(@NotNull CharSequence charSequence) {
            List<Movie> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredList.addAll(movieList);
            } else {
                String result =charSequence.toString().toLowerCase().trim();
                for (Movie movie : movieList){
                    if (movie.getTitle().toLowerCase().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if (movie.getYear().toLowerCase().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if (movie.getReleaseDate().toLowerCase().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if (movie.getDuration().toLowerCase().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if ((movie.getImdbRating()+"").contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if (movie.getActors().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                    if (movie.getGenres().contains(result.toLowerCase()))
                        filteredList.addAll((Collection<? extends Movie>) movie);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        //run on ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            movieList.clear();
            movieList.addAll((Collection<? extends Movie>) filterResults.values);
            notifyDataSetChanged();

        }
    };
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView movie_title;
        ImageView movie_poster;
        CardView movie_cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_title = (TextView)itemView.findViewById(R.id.movie_title_id);
            movie_poster = (ImageView)itemView.findViewById(R.id.movie_img_id);
            movie_cardView  = (CardView)itemView.findViewById(R.id.movie_cardView);
        }
    }
}
