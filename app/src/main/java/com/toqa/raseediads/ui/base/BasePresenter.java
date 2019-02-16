package com.toqa.raseediads.ui.base;

public class BasePresenter<V extends BaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }
}
