package com.example.happyenglish.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static volatile DataBase INSTANCE;

    public abstract UserDao userDao();

    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBase.class, "English_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
