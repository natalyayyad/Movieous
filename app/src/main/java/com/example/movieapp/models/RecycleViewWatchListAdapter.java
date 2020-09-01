package com.example.movieapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.R;

import com.example.movieapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RecycleViewWatchListAdapter  extends RecyclerView.Adapter<RecycleViewWatchListAdapter.MyViewHolder>{
    private Context mContext;
    private List<String> mID;
    private List<String> mTitle;
    private List<String> mPosterURL;
    private List<String>mDate;
    private List<String>mTime;

    public RecycleViewWatchListAdapter(Context mContext, List<String> mID,  List<String> mTitle, List<String> mPosterURL, List<String>mDate, List<String>mTime) {
        this.mContext = mContext;
        this.mID = mID;
        this.mTitle = mTitle;
        this.mPosterURL = mPosterURL;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    @NonNull
    @Override
    public RecycleViewWatchListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_watch, parent, false);
        return new RecycleViewWatchListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewWatchListAdapter.MyViewHolder holder, int position) {
        holder.watch_title.setText(mTitle.get(position));
        Picasso.with(mContext).load(mPosterURL.get(position)).fit().centerCrop().into(holder.watch_poster);
        holder.watch_time.setText(mTime.get(position));
        holder.watch_date.setText(mDate.get(position));
    }

    @Override
    public int getItemCount() {
        return mID.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView watch_title;
        ImageView watch_poster;
        TextView watch_time;
        TextView watch_date;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            watch_title = (TextView)itemView.findViewById(R.id.watch_title_id);
            watch_poster = (ImageView)itemView.findViewById(R.id.watch_img_id);
            watch_time = (TextView) itemView.findViewById(R.id.watch_time);
            watch_date = (TextView)itemView.findViewById(R.id.watch_date);

        }
    }
}
