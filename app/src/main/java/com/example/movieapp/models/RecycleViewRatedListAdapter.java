package com.example.movieapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewRatedListAdapter extends RecyclerView.Adapter<RecycleViewRatedListAdapter.MyViewHolder>{
    private Context mContext;
    private List<String> mID;
    private List<String> mTitle;
    private List<String> mRate;
    private List<String> mURL;

    public RecycleViewRatedListAdapter(Context mContext, List<String> mID, List<String> mTitle, List<String> mURL, List<String> mRate) {
        this.mContext = mContext;
        this.mID = mID;
        this.mTitle = mTitle;
        this.mURL = mURL;
        this.mRate = mRate;
    }

    @NonNull
    @Override
    public RecycleViewRatedListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_rated, parent, false);
        return new RecycleViewRatedListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewRatedListAdapter.MyViewHolder holder, int position) {
        holder.rated_title.setText(mTitle.get(position));
        Picasso.with(mContext).load(mURL.get(position)).fit().centerCrop().into(holder.rated_poster);
        holder.rated_ratingValue.setText(mRate.get(position));
        holder.rated_ratingBar.setRating(Float.parseFloat(mRate.get(position)));

    }

    @Override
    public int getItemCount() {
        return mID.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rated_title;
        ImageView rated_poster;
        RatingBar rated_ratingBar;
        TextView rated_ratingValue;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rated_title = (TextView)itemView.findViewById(R.id.rated_title_id);
            rated_poster = (ImageView)itemView.findViewById(R.id.rated_img_id);
            rated_ratingBar = (RatingBar)itemView.findViewById(R.id.rate_ratingBar);
            rated_ratingValue = (TextView)itemView.findViewById(R.id.rate_value);
        }
    }
}
