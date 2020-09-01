package com.example.movieapp.activities.ui.home;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.movieapp.R;

public class HomeFragment extends Fragment {
    ImageView imageView;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = root.findViewById(R.id.home_logo);
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.bounce));
        //onStart();
        //onResume();
        //TransitionDrawable transitionDrawable = (TransitionDrawable) imageView.getDrawable();
        //transitionDrawable.reverseTransition(1000);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }

    @Override
    public void onStart() {
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.bounce));
        super.onStart();
    }

    @Override
    public void onResume() {
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.bounce));
        super.onResume();
    }
}