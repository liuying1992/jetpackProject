package com.liuying.jetpackproject.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Query("select * from tab_user")
    Single<List<UserInfo>> getAll();

    @Insert
    Completable insertAll(UserInfo... userInfo);

    @Query("delete from tab_user where userName = :userName")
    Single<Integer> delete(String userName);

    @Update
    Completable update(UserInfo... userInfo);
}
