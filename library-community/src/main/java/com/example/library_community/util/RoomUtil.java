package com.example.library_community.util;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:17:58
 * author:yanghaoyang
 */
@Dao
public abstract class RoomUtil<T> {
    private static final String TAG = "DataBeanDao";
    private String tableName;
    //构造时 通过反射获取表名

    public RoomUtil() {
    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
    Class tableNameClass = (Class) genericSuperclass.getActualTypeArguments()[0];
        tableName = tableNameClass.getSimpleName();
        LogUtils.i(tableName);

    }
    public void inserts(T...bean){
        insert(bean);
    };
    public void upDatas(T...bean){
        upData(bean);
    }
    public void deletes(T...bean){
        delete(bean);
    }
    @Insert
    protected abstract  void insert(T...bean);
    @Update
    protected abstract void upData(T...bean);
    @Delete
    protected abstract void delete(T...bean);
    //查询单个
    @RawQuery
    protected abstract T find(SupportSQLiteQuery query);
    public T find(Long id){
    SimpleSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM "+tableName+" WHERE id = ?",new Long[]{id});
    return find(query);
    }
    //模糊查询
    @RawQuery
    protected abstract List<T> fuzzyFind(SupportSQLiteQuery query);
    //删除所有
    @RawQuery
    protected abstract Integer deleteAll(SupportSQLiteQuery query);
    public Integer deleteAll(){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery("DELETE FROM "+tableName);
        return deleteAll(query);
    }
    //查询所有
    @RawQuery
    protected abstract List<T> findAll(SupportSQLiteQuery query);
    public List<T> findAll(){
      SupportSQLiteQuery query =new SimpleSQLiteQuery("SELECT * FROM "+tableName);
      return findAll(query);
    }

}
