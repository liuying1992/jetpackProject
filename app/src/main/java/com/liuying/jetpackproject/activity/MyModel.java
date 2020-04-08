package com.liuying.jetpackproject.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyModel extends ViewModel {
    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getData() {
        return mutableLiveData;
    }

}
