package com.example.myapplication.di;

import androidx.room.Room;

import com.example.myapplication.App;
import com.example.myapplication.model.cache.Database;
import com.example.myapplication.model.cache.RoomCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Singleton
    @Provides
    public Database getDatabase(App app) {
        return Room.databaseBuilder(app, Database.class, Database.DB_NAME).fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    RoomCache getRoomCache(Database database) {
        return new RoomCache(database);
    }
}
