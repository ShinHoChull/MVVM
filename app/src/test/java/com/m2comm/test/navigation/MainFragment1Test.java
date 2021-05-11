package com.m2comm.test.navigation;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainFragment1Test {

    MainFragment1 mainFragment1;

    @Test
    public void numberPlus() {
        mainFragment1 = new MainFragment1();

        assertEquals(60 , mainFragment1.numberPlus(10, 20));
    }

    private int plusNumber(int num1 , int num2) {
        return num1 + num2;
    }

}