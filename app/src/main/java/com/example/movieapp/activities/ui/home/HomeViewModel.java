package com.example.movieapp.activities.ui.home;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ImageView>mImage;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Movious" + "\n" + "your unlimited movies hub!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}