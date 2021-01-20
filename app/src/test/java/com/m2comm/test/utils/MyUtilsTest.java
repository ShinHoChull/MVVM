package com.m2comm.test.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void sum() throws Exception {
        int result = MyUtils.sum(10 , 20);

        //case 1
        Assert.assertEquals(30 , result);
//        //case 2
//        Assert.assertEquals(29 , result);
//        //case 3
//        Assert.assertEquals(26 , result);
    }
}