package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity")
    List<ProfileEntity> getAll();

    @Query("SELECT * FROM ProfileEntity WHERE id = :id")
    ProfileEntity getById(int id);

    @Insert
    void insert(ProfileEntity profile);

    @Update
    void update(ProfileEntity profile);

    @Delete
    void delete(ProfileEntity profile);
}
