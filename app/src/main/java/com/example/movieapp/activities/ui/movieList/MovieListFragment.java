package com.example.movieapp.activities.ui.movieList;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.activities.HomeActivity;
import com.example.movieapp.activities.MainActivity;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.RecycleViewMovieListAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class MovieListFragment extends Fragment {
    @BindView(R.id.recycleView_id)
    RecyclerView _recycleView;

    ;

    private MovieListViewModel mViewModel;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        _recycleView = view.findViewById(R.id.recycleView_id);
        ArrayList<Movie>movies = MainActivity.Movies;
        if (movies.size() ==0)
            Toast.makeText(getContext(), "No Available Movies",Toast.LENGTH_SHORT).show();
        final RecycleViewMovieListAdapter movieAdapter = new RecycleViewMovieListAdapter(getContext(), MainActivity.Movies);
        _recycleView.setLayoutManager(new GridLayoutManager(getContext(),3));
        _recycleView.setAdapter(movieAdapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home,menu);
        MenuItem item = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                RecycleViewMovieListAdapter movieAdapter = new RecycleViewMovieListAdapter(getContext(), MainActivity.Movies);
                movieAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}