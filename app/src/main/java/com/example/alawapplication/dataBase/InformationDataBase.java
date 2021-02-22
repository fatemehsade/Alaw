package com.example.alawapplication.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.alawapplication.model.InformationItems;


@Database(entities = {InformationItems.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class InformationDataBase extends RoomDatabase{

        private static InformationDataBase sDataBase;
        private static final String DB_NAME = "information.db";

        public synchronized static InformationDataBase getInstance(Context context) {
            if (sDataBase == null) {
                sDataBase = Room.databaseBuilder(context.getApplicationContext(),
                        InformationDataBase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return sDataBase;
        }

        public abstract InformationDao getInformationDao();
}
