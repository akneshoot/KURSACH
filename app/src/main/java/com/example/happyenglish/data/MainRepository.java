package com.example.happyenglish.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

public class MainRepository implements MainProtocol{
    private Context context;
    private DBRemoteDataSource dataSource;
    public MainRepository(Context context) {
        this.context = context;
        dataSource = new DBRemoteDataSource(context);
    }
    @Override
    public LiveData<Boolean> login(String login, String pass) {
        return dataSource.Login(login, pass);
    }
    @Override
    public void registration(User user) {
        dataSource.registration(user);
    }

}

