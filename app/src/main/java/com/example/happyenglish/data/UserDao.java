package com.example.happyenglish.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getUserList();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);
    @Query("SELECT * FROM user WHERE :id LIKE id")
    LiveData<User> getUser(int id);
    @Query("SELECT EXISTS(SELECT * FROM user WHERE login=:login AND pass=:pass)")
    LiveData<Boolean> getUserByLogin(String login, String pass);
}
