package com.example.happyenglish.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

public class DBRemoteDataSource {
    private final Context context;
    public DBRemoteDataSource(Context context) {
        this.context = context;
    }
    public LiveData<Boolean> Login(String login, String pass) {
        DataBase db = DataBase.getDatabase(context);
        UserDao userDao = db.userDao();
        return userDao.getUserByLogin(login, pass);
    }
    public void registration(User user) {
        DataBase db = DataBase.getDatabase(context);
        UserDao userDao = db.userDao();
        db.getQueryExecutor().execute(() -> {
            userDao.insert(user);
        });
    }
}
