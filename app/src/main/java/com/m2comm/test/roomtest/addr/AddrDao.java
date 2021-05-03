package com.m2comm.test.roomtest.addr;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddrDao {
    @Query("select * from ADDR_VZZ")
    List<ADDR_VZZ> getAll();

    @Query("select count(*)as count from ADDR_VZZ")
    int getAllCount();
}
