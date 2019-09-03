package com.example.myapplication.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ProfileEntity.class}, version = 1)

public abstract class ProfileData extends RoomDatabase {
    public abstract ProfileDao profileDao();
}
