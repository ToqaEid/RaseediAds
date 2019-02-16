package com.toqa.raseediads.model.datamanger;

import android.content.Context;

import com.toqa.raseediads.R;
import com.toqa.raseediads.model.network.ServiceGenerator;
import com.toqa.raseediads.model.network.apis.AdService;
import com.toqa.raseediads.model.pojo.Ad;
import com.toqa.raseediads.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * AdsDataManager class is a class used to manage data
 * and returned the final data to the presenter
 * for example returning data whether from network or DB(if exists)
 */
public class AdsDataManager {

    /**
     * Callbacks Interface for AdsDataManager of the results
     */
    public interface AdsManagerCallbacks {
        /**
         * will be pass result on Success
         *
         * @param adList list passed in callback when success
         */
        void onSuccess(List<Ad> adList);

        /**
         * will pass error on failure
         *
         * @param state determine the state of error whether general error like connection error
         *              or response error like 404 from network.
         * @param msg   error msg
         */
        void onFailure(int state, String msg);
    }

    private Context context;

    /**
     * Solo constructor with 1 param
     *
     * @param context context in which AdsDataManager is created
     */
    public AdsDataManager(Context context) {
        this.context = context;
    }

    /**
     * Method used to getAds
     *
     * @param callback AdManagetCallback that will carry the result
     */
    public void getAds(AdsManagerCallbacks callback) {
        getNetworkAds(callback);
    }


    private void getNetworkAds(final AdsManagerCallbacks callback) {
        AdService adService = ServiceGenerator.createService(AdService.class);
        Call<List<Ad>> adsCall = adService.getAds(false);
        adsCall.enqueue(new Callback<List<Ad>>() {

            @Override
            public void onFailure(Call<List<Ad>> call, Throwable t) {
                callback.onFailure(Utils.STATE_FAILURE_GENERAL_ERROR, context.getString(R.string.connection_error));
            }

            @Override
            public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(Utils.STATE_FAILURE_RESPONSE_ERROR, response.message());
                }
            }
        });
    }
}