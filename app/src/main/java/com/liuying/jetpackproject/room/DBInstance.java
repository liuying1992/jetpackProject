package com.liuying.jetpackproject.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 单例创建数据库
 */
public class DBInstance {
    private static final String DB_NAME = "my_db";
    public static MyDatabase myDatabase;
    private static Context mContext = null;
    static final Migration MIGRATION_1 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE tab_user ADD COLUMN address TEXT");
        }
    };


    public static MyDatabase getInstance() {
        if (myDatabase == null) {
            synchronized (DBInstance.class) {
                if (myDatabase == null) {
                    myDatabase = Room.databaseBuilder(mContext, MyDatabase.class, DB_NAME)
//                            .addMigrations(MIGRATION_1)//数据库升级
                            .build();
                }
            }
        }
        return myDatabase;
    }

    public static void init(Context applicationContext) {
        mContext = applicationContext;
        getInstance();
    }
}
