package service;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.NetworkUtil;

/**
 * Created by fvillela on 5/15/17.
 */

public class MarvelApi {
    private static String baseUrl = "https://gateway.marvel.com:443/";
    private static MarvelApi instance = null;
    private static Retrofit retrofit;
    private ApiService apiService;

    public static void initMarvelApi() {
        if (instance == null) {
            instance = new MarvelApi();
        }
    }

    private MarvelApi() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static <T> void executeRequest(final Call<T> baseCall, final Context context, final RequestListener listener) {

        if (!NetworkUtil.isNetworkAvailable(context)) {
            listener.onRequestFail(ServiceErrors.CONNECTION_UNAVAILABLE.getCode(), ServiceErrors.CONNECTION_UNAVAILABLE.getDescription());
            return;
        }

        baseCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.isSuccessful()) {
                    listener.onRequestSuccess(response.code(), response);
                } else {
                    listener.onRequestFail(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onRequestFail(ServiceErrors.UNKNOWN.getCode(), t.getLocalizedMessage());
            }
        });
    }
}
