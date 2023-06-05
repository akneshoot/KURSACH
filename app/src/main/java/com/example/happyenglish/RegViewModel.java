package com.example.happyenglish;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.happyenglish.data.MainRepository;
import com.example.happyenglish.data.User;

public class RegViewModel extends AndroidViewModel {
    public RegViewModel(@NonNull Application application) {
        super(application);
    }

    private MainRepository repository = new MainRepository(getApplication());

    public void registration(User user) {
        repository.registration(user);
    }
}
