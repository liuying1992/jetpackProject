package com.liuying.jetpackproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tab_user")
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "userName")
    private String userName;
    @ColumnInfo(name = "sex")
    private String sex;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "address")
    private String address;

    public UserInfo() {

    }

    public UserInfo(String name, String sex, int age) {
        this.userName = name;
        this.sex = sex;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
