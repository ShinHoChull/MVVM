package com.m2comm.test.roomtest.basecode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BaseCodeDAO {

    @Query("select * from BaseCodeVO where GROUP_ID = :codeValue")
    List<BaseCodeVO>getCodeList(String codeValue);


    @Query("select * from BaseCodeVO")
    List<BaseCodeVO>getAllList();

    @Query("delete from BaseCodeVO")
    void allDelete();

    @Insert
    void insert(BaseCodeVO baseCodeVO);

    @Update
    void update(BaseCodeVO baseCodeVO);

    @Delete
    void delete(BaseCodeVO baseCodeVO);

}
