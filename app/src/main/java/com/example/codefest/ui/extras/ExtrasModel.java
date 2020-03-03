package com.example.codefest.ui.extras;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExtrasModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ExtrasModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is extras fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
