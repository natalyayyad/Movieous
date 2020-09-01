package com.example.movieapp.activities.ui.watchList;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.activities.HomeActivity;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.models.RecycleViewWatchListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WatchListFragment extends Fragment {

    @BindView(R.id.watch_recycleView_id)
    RecyclerView _recycleView;
    List<String> watchID;
    List<String> watchTitle;
    List<String> watchURL;
    List<String> watchDate;
    List<String> watchTime;
    private WatchListViewModel mViewModel;

    public static WatchListFragment newInstance() {
        return new WatchListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.watch_list_fragment, container, false);
        _recycleView = view.findViewById(R.id.watch_recycleView_id);

        DataBaseHelper mydb = new DataBaseHelper(getContext(),"WATCHLIST",null,4);
        Cursor c = mydb.getWatchList(HomeActivity.shared);
        watchID = new ArrayList<>();
        watchTitle = new ArrayList<>();
        watchURL = new ArrayList<>();
        watchDate = new ArrayList<>();
        watchTime = new ArrayList<>();

        if(c.getCount()==0)
            Toast.makeText(getContext(), "Watch List is Empty",Toast.LENGTH_LONG).show();
        else {
            while (c.moveToNext()) {
                watchID.add(c.getString(1));
                watchTitle.add(c.getString(2));
                watchDate.add(c.getString(3));
                watchTime.add(c.getString(4));
                watchURL.add(c.getString(5));

            }
            RecycleViewWatchListAdapter watchAdapter = new RecycleViewWatchListAdapter(getContext(), watchID, watchTitle, watchURL,watchDate, watchTime);
            _recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            _recycleView.setAdapter(watchAdapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WatchListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}