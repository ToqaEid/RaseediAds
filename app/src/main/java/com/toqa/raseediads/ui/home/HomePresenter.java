package com.toqa.raseediads.ui.home;

import com.toqa.raseediads.model.datamanger.AdsDataManager;
import com.toqa.raseediads.model.pojo.Ad;
import com.toqa.raseediads.ui.base.BasePresenter;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeView> implements AdsDataManager.AdsManagerCallbacks {

    private AdsDataManager adsDataManager;

    /**
     * Constructor with 2 params
     *
     * @param homeView
     * @param adsDataManager
     */
    public HomePresenter(HomeView homeView, AdsDataManager adsDataManager) {
        super(homeView);
        this.adsDataManager = adsDataManager;
    }

    /**
     * Methods that gets ads from DataManager and handle the data
     */
    public void getAds() {
        if (view != null) {
            view.showLoading();
            adsDataManager.getAds(this);
        }
    }

    @Override
    public void onSuccess(List<Ad> adList) {
        if (view != null) {
            view.hideLoading();
            view.showAds(adList);
        }
    }

    @Override
    public void onFailure(int state, String msg) {
        if (view != null) {
            view.showError(state, msg);
        }
    }

}
