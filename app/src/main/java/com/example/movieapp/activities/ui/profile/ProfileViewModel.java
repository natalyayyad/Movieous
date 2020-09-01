package com.example.movieapp.activities.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    // TODO: Implement the ViewModel
    public ProfileViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is The profile Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}