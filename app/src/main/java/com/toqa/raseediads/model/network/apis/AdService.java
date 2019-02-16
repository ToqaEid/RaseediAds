package com.toqa.raseediads.model.network.apis;

import com.toqa.raseediads.model.pojo.Ad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AdService {

    @GET("get_ad/")
    Call<List<Ad>> getAds(@Query("solo") boolean solo);

}
