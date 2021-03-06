package com.toqa.raseediads.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.toqa.raseediads.R;
import com.toqa.raseediads.model.datamanger.AdsDataManager;
import com.toqa.raseediads.model.pojo.Ad;
import com.toqa.raseediads.ui.base.BaseActivity;
import com.toqa.raseediads.ui.details.AdDetailsActivity;
import com.toqa.raseediads.utils.UiUtils;
import com.toqa.raseediads.utils.Utils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView, AdsAdapter.AdClickListener {

    @BindView(R.id.recyclerView_ads)
    RecyclerView recyclerView_ads;

    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;

    private AlertDialog.Builder alertBuilder;
    private AdsAdapter adsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, new AdsDataManager(this));

        presenter.getAds();
    }

    @Override
    protected void initView() {
        super.initView();

        Fresco.initialize(this);
        adsAdapter = new AdsAdapter();
        adsAdapter.setAdClickListener(this);
        recyclerView_ads.setAdapter(adsAdapter);
        recyclerView_ads.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    /**
     * Method for showing progressBar
     */
    @Override
    public void showLoading() {
        hideLoading();
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Method for hiding progressBar
     */
    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Method for filling data in the recyclerView
     *
     * @param adList list that will be sorted and added in the recyclerView
     */
    @Override
    public void showAds(List<Ad> adList) {
        if (adList != null) {
            adsAdapter.clear();
            Collections.sort(adList);
            adsAdapter.addItems(adList);
        }
    }

    /**
     * Show error dialog according to @param state
     *
     * @param state int param indicator for the error state
     *              STATE_FAILURE_GENERAL_ERROR :  General error for example No Internet Connection, SocketTimeoutConnection, etc.
     *              STATE_FAILURE_RESPONSE_ERROR: Response error for example 404, 500, etc.
     * @param msg   error message that will be displayed
     */
    @Override
    public void showError(int state, String msg) {

        progressBar.setVisibility(View.INVISIBLE);

        DialogInterface.OnClickListener positiveBtnListener = (dialogInterface, i) -> {
            dialogInterface.cancel();
        };

        switch (state) {
            case Utils.STATE_FAILURE_RESPONSE_ERROR:
                //show OK button only that will dismiss the error
                alertBuilder = UiUtils.initAlertDialog(HomeActivity.this, getString(R.string.error_title), msg,
                        0, positiveBtnListener, null);
                break;
            case Utils.STATE_FAILURE_GENERAL_ERROR:
                //1- show OK button only tht will dismiss the error
                //2- retry button that will call getAds method again
                DialogInterface.OnClickListener neutralBtnListener = (dialogInterface, i) -> {
                    presenter.getAds();
                    dialogInterface.cancel();
                };
                alertBuilder = UiUtils.initAlertDialog(HomeActivity.this, getString(R.string.error_title), msg,
                        R.string.retry, positiveBtnListener, neutralBtnListener);
                break;
        }
        alertBuilder.show();
    }

    @Override
    public void onAdClick(Ad ad) {
        Intent intent = new Intent(HomeActivity.this, AdDetailsActivity.class);
        intent.putExtra(Utils.EXTRA_AD, ad);
        startActivity(intent);
    }
}
