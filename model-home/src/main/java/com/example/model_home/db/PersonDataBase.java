package com.example.model_home.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:18:52
 * author:yanghaoyang
 */
@Database(entities = Person.class,version = 1,exportSchema = false)
public abstract class PersonDataBase extends RoomDatabase {
    private final static String DB_NAME = "person.db";
    private static PersonDataBase personDataBase;

    public static PersonDataBase getInstance(Context context) {
        if (personDataBase==null){
            synchronized (PersonDataBase.class){
                if (personDataBase==null){
                    personDataBase = Room.databaseBuilder(context
                    ,PersonDataBase.class,DB_NAME).build();
                }
            }
        }
        return personDataBase;
    }
    public abstract PersonDao getPersonDao();
}
