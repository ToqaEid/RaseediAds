package com.toqa.raseediads.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toqa.raseediads.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ServiceGenerator class is used to create Retrofit Services
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(APIHelper.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    /**
     * General class that is used to create retrofit serviceClasses
     *
     * @param serviceClass
     * @return T created serviceClass
     */
    public static <T> T createService(Class<T> serviceClass) {
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        if (retrofit == null) {
            retrofit = builder.client(
                    httpClient.addInterceptor(interceptor)
                            .connectTimeout(APIHelper.RETROFIT_REQUEST_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                            .readTimeout(APIHelper.RETROFIT_REQUEST_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                            .writeTimeout(APIHelper.RETROFIT_REQUEST_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS).build()).build();
        }
        return retrofit.create(serviceClass);
    }
}
