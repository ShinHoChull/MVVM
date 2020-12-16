package com.m2comm.test.main;

public class MainPresenter implements MainConstants.Presenter {

    MainConstants.View mainView;
    MainModel mainModel;


    public MainPresenter( MainConstants.View view ) {
        //view 연결
        this.mainView = view;

        //Model 연결
        this.mainModel = new MainModel(this);
    }

    @Override
    public void plus(int input) {
        
    }

    @Override
    public void minus(int input) {

    }

    @Override
    public void times(int input) {

    }

    @Override
    public void divided(int input) {

    }

    @Override
    public void cancel() {

    }
}
