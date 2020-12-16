package com.m2comm.test.main;

public interface MainConstants {

    public interface View {
        void showResult(int result);
    }

    public interface Presenter {
        void plus ( int input );
        void minus ( int input );
        void times ( int input );
        void divided ( int input );
        void cancel ();

    }

}
