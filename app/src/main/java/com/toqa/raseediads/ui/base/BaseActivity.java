package com.toqa.raseediads.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * General Parent class for activities for common features
 * for example ButterKnife binding
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        ButterKnife.bind(this);
        initView();
    }

    /**
     * used by activity to initialize ui attributes
     * for example initializing recycler view and assigning adapter to it
     */
    protected void initView() {

    }

    /**
     * Abstract method must be implemented by child activities
     * to @return layout file that will be used in creating the ui of the activity
     */
    protected abstract int getLayoutResource();

}
