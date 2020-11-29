package com.suresh.sharedpreferences.DbUtils;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//implement and create database

@Database(entities = {User.class},version = 1)
//extents room data base and create abstract class
public abstract class LocalDB extends RoomDatabase {
    public abstract UserDao userDao();
}
