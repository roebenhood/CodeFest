package com.example.codefest.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.codefest.MainActivity;

public class SlideshowViewModel extends ViewModel {

    MainActivity mainActivity = new MainActivity();

    public String toBePassed = mainActivity.welcomeMessage;

    public MutableLiveData<String> mText;
    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("wangkaw");
    }

    public LiveData<String> getText() {
        return mText;
    }
}