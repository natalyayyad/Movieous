package com.example.movieapp.activities.ui.ratedList;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.activities.HomeActivity;
import com.example.movieapp.activities.MovieDetailsActivity;
import com.example.movieapp.activities.ui.contactus.ContactUsFragment;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.RecycleViewMovieListAdapter;
import com.example.movieapp.models.RecycleViewRatedListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class RatedListFragment extends Fragment {

    @BindView(R.id.rated_recycleView_id)
    RecyclerView _recycleView;
    List<String> rateID;
    List<String> rateTitle;
    List<String> rateURL;
    List<String> rateValue;

    private RatedListViewModel mViewModel;

    public static RatedListFragment newInstance() {
        return new RatedListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rated_list_fragment, container, false);
        _recycleView = view.findViewById(R.id.rated_recycleView_id);
        DataBaseHelper mydb = new DataBaseHelper(getContext(),"RATEDLIST",null,4);
        Cursor c = mydb.getRatedList(HomeActivity.shared);
        rateID = new ArrayList<>();
        rateTitle = new ArrayList<>();
        rateURL = new ArrayList<>();
        rateValue = new ArrayList<>();
        if(c.getCount()==0)
            Toast.makeText(getContext(), "Rated Movies List is Empty",Toast.LENGTH_SHORT).show();
        else {
            while (c.moveToNext()) {
                rateID.add(c.getString(1));
                rateTitle.add(c.getString(2));
                rateValue.add(c.getString(3));
                rateURL.add(c.getString(4));
                
            }
            RecycleViewRatedListAdapter RatedAdapter = new RecycleViewRatedListAdapter(getContext(), rateID, rateTitle,rateURL,rateValue);
            _recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            _recycleView.setAdapter(RatedAdapter);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RatedListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}