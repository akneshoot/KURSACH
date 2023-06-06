package com.example.happyenglish.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.regex.Pattern;

@Entity(indices = {@Index(value = {"login"}, unique = true)})
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

    public User(String email, String login, String pass) throws InvalidEmailException, InvalidPasswordException, InvalidNameException {
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Email is incorrect");
        }
        if (!isValidPassword(pass)) {
            throw new InvalidPasswordException("Password is incorrect");
        }
        if (!isValidName(login)) {
            throw new InvalidNameException("Name is incorrect");
        }
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

    private boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(mail\\.ru|gmail\\.com|yandex\\.ru)$";
        return Pattern.matches(emailPattern, email);
    }

    private boolean isValidPassword(String password) {
        // Add your password validation logic here
        // For example, check for minimum length, required characters, etc.
        return password.length() >= 6; // Minimum 6 characters
    }

    private boolean isValidName(String name) {
        // Add your name validation logic here
        // For example, check for minimum length, allowed characters, etc.
        return name.length() >= 2; // Minimum 2 characters
    }

    @NonNull
    public Integer getUid() {
        return uid;
    }

    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    public static class InvalidPasswordException extends Exception {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }
}
