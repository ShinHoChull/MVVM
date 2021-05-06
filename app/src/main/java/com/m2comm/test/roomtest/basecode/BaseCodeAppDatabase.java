package com.m2comm.test.roomtest.basecode;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.m2comm.test.roomtest.Todo;

@Database(entities = {BaseCodeVO.class} , version = 1, exportSchema = false)
public abstract class BaseCodeAppDatabase extends RoomDatabase {
    public abstract BaseCodeDAO baseCodeDAO();
}
