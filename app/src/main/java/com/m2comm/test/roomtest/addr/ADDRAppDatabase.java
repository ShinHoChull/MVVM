package com.m2comm.test.roomtest.addr;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.m2comm.test.roomtest.TodoDao;

@Database(entities = {ADDR_VZZ.class} , version = 1 , exportSchema = false)
public abstract class ADDRAppDatabase extends RoomDatabase {
    public abstract AddrDao addrDao();
}


