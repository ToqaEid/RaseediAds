package com.toqa.raseediads.ui.base;

public class BasePresenter<V extends BaseView> {

    protected V view;

    /**
     * Solor constructor with single parameter
     *
     * @param view class that implements BaseView interface or its children
     */
    public BasePresenter(V view) {
        this.view = view;
    }
}
