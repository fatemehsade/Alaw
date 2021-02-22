package com.example.alawapplication.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alawapplication.model.InformationItems;

import java.util.List;

@Dao
public interface InformationDao {

    @Insert
    void insert(InformationItems items);

    @Query("select * from information_table")
    List<InformationItems> getItems();


    @Update
    void updateItem(List<InformationItems> items);

}
