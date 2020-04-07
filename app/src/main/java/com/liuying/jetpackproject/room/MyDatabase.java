package com.liuying.jetpackproject.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * 数据库
 */
@Database(entities = {UserInfo.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
