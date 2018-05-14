package com.example.trope.myapplication;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BirthdayDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}