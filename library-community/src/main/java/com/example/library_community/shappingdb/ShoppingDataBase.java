package com.example.library_community.shappingdb;

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
 * Date:2021/12/15
 * Time:19:31
 * author:YangHaoYang
 * Package com.example.library_community.shappingdb
 */
@Database(entities =ShoppingBean.class,version = 1,exportSchema = false)
public abstract class ShoppingDataBase extends RoomDatabase {
    private final static String SHOPPING_NAME = "shopping.db";
    private  static volatile ShoppingDataBase  shoppingDataBase = null;

    public static ShoppingDataBase getInstance(Context context) {
        if (shoppingDataBase==null){
            synchronized (ShoppingDataBase.class){
                if (shoppingDataBase==null){
                    shoppingDataBase = Room.databaseBuilder(context.getApplicationContext(),ShoppingDataBase.class,SHOPPING_NAME)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return shoppingDataBase;
    }
    public abstract ShoppingDao getShoppingDao();
}
