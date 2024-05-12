package com.example.happyenglish;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.happyenglish.data.MainRepository;

public class VhodViewModel extends AndroidViewModel {
    private final MainRepository repository = new MainRepository(getApplication());


    public LiveData<Boolean> loginV(String login, String pass) {
        return repository.login(login, pass);
    }

    public VhodViewModel(@NonNull Application application) {
        super(application);
    }
}
