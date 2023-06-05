package com.example.happyenglish.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"login"},unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public Integer uid;

    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "login")
    public String login;

    @ColumnInfo(name = "pass")
    public String pass;

    public User(String email, String login, String pass) {
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    public Integer getUid() {
        return uid;
    }
}
