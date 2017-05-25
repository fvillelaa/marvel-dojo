package service;

import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;

import java.util.List;

import listeners.APIResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fvillela on 5/15/17.
 */

public class MarvelApiImpl {
    private static String baseUrl = "https://gateway.marvel.com:443/";
    private static MarvelApiImpl instance = null;
    private static Retrofit retrofit;

    public static void initMarvelApi() {
        if (instance == null) {
            instance = new MarvelApiImpl();
        }
    }

    private MarvelApiImpl() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static <T> void executeRequest(final Call<T> baseCall, final APIResponseListener apiResponseListener) {
        baseCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                MarvelResultWrapper body = (MarvelResultWrapper) response.body();
                List<Comic> results = body.getData().getResults();
                apiResponseListener.onSuccess(results);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                apiResponseListener.onError(t.getMessage().toString());
            }
        });
    }
}
