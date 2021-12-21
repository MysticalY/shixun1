package com.example.library_community.addressdb;

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
 * Date:2021/12/18
 * Time:15:53
 * author:YangHaoYang
 * Package com.example.library_community.addressdb
 */
@Database(entities = AddressBean.class,version = 1,exportSchema = false)
public abstract class AddressDataBase extends RoomDatabase {
    private final static String ADDRESS_NAME = "address.db";
    private static volatile AddressDataBase addressDataBase = null;

    public static AddressDataBase getInstance(Context context) {
        if (addressDataBase ==null){
            synchronized (AddressDataBase.class){
                if (addressDataBase==null){
                    addressDataBase = Room.databaseBuilder(context.getApplicationContext(),AddressDataBase.class,ADDRESS_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return addressDataBase;
    }
    public abstract AddressDao getAddressDao();
}
