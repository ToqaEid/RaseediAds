package com.toqa.raseediads.ui.home;

import com.toqa.raseediads.model.pojo.Ad;
import com.toqa.raseediads.ui.base.BaseView;

import java.util.List;

public interface HomeView extends BaseView {

    void showLoading();

    void hideLoading();

    void showAds(List<Ad> adList);

    void showError(int state, String msg);
}
