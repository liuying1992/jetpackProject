package com.liuying.jetpackproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<String>> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(new ArrayList<String>() {{
            add("111");
            add("222");
        }});
    }

    public LiveData<List<String>> getText() {
        return mText;
    }
}