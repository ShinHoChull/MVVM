package com.m2comm.test.rx_test;

public interface RxContranst {
    interface View {
        void showResult(int result);
    }

    interface Presenter {
        void plus(int num1 , int num2);
    }
}
