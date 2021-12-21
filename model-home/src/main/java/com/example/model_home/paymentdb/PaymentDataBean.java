package com.example.model_home.paymentdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.library_community.shappingdb.ShoppingDao;
import com.example.library_community.shappingdb.ShoppingDataBase;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/20
 * Time:10:10
 * author:YangHaoYang
 * Package com.example.model_home.paymentdb
 */
@Database(entities = PaymentBean.class,version = 1,exportSchema = false)
public abstract class PaymentDataBean extends RoomDatabase {
    private final static String PAYMENT_NAME = "payment.db";
    private  static volatile PaymentDataBean paymentDataBean = null;

    public static PaymentDataBean getInstance(Context context) {
        if (paymentDataBean==null){
            synchronized (ShoppingDataBase.class){
                if (paymentDataBean==null){
                    paymentDataBean = Room.databaseBuilder(context.getApplicationContext(),PaymentDataBean.class,PAYMENT_NAME)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return paymentDataBean;
    }
    public abstract PaymentDao getPaymentDao();
}
