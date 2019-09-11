package com.example.myapplication.model.cache.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model.cache.Wash;

import java.util.List;

@Dao
public interface WashDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Wash wash);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Wash> washes);

    @Query("SELECT * FROM wash")
    List<Wash> getAll();
}
