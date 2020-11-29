package com.suresh.sharedpreferences;

import android.app.Application;

import androidx.room.Room;

import com.suresh.sharedpreferences.DbUtils.LocalDB;

public class RoomImplementation extends Application {
    private  static RoomImplementation mInstance;
    private LocalDB  dbInstantce;
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        dbInstantce = Room.databaseBuilder(getApplicationContext(), LocalDB.class,"LocalDB").build();
    }
    public static RoomImplementation getmInstance() {
        return mInstance;
    }
    public LocalDB getDbInstantce () {
        return dbInstantce;
    }
}
