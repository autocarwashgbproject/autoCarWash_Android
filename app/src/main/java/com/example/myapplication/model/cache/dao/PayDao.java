package com.example.myapplication.model.cache.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.myapplication.model.cache.Pay;

import java.util.List;

@Dao
public interface PayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pay pay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Pay> pays);

    /*@Query("SELECT * FROM pays")
    List<Pay> getAll();*/
}
