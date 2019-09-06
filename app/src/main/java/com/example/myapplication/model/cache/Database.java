package com.example.myapplication.model.cache;


import androidx.room.RoomDatabase;

import com.example.myapplication.model.cache.dao.PayDao;
import com.example.myapplication.model.cache.dao.WashDao;

@androidx.room.Database(entities = {Pay.class, Wash.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DB_NAME = "history.db";

    public abstract PayDao getPay();

    public abstract WashDao getWash();
}
