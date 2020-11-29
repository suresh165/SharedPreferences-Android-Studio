package com.suresh.sharedpreferences.DbUtils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//create interface class
@Dao
public interface UserDao {
    @Insert
    void createUser(User user);
    @Query("SELECT * FROM user where username like :strUsername")
    User getUserByUsername(String strUsername);
}
