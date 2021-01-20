package com.m2comm.test.memo.db;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoFacadeTest {
    Context mContext;
    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        mContext = null;
    }

    @Test
    public void insert() {
        MemoFacade facade = new MemoFacade(mContext);
        long result = facade.insert("hello","test","test");
        Assert.assertNotEquals(-1, result);
    }

    @Test
    public void getMemoAllList() {
    }

    @Test
    public void itemUpdate() {
    }

    @Test
    public void itemDelete() {
    }

    @Test
    public void close() {
    }
}