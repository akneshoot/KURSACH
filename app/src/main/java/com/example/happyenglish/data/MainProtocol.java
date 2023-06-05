package com.example.happyenglish.data;

import androidx.lifecycle.LiveData;

public interface MainProtocol {
    void registration(User user);
    LiveData<Boolean>login(String login,String pass);

}
