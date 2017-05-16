package controller;

import android.abinbev.com.marveldojo.model.MarvelResultWrapper;
import android.content.Context;

import listeners.LoadDataListener;
import retrofit2.Call;
import retrofit2.Response;
import service.ApiService;
import service.MarvelApi;
import service.MarvelApiRequestSignature;
import service.RequestListener;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsController implements RequestListener {

    private MarvelResultWrapper marvelResultWrapper;
    private LoadDataListener loadDataListener;
    private int limitComics = 20;

    public void getComicsList(Context context, LoadDataListener loadDataListener) {
        this.loadDataListener = loadDataListener;

        MarvelApiRequestSignature marvelApiRequestSignature = new MarvelApiRequestSignature();

        MarvelApi.initMarvelApi();

        ApiService apiService = MarvelApi.getRetrofit().create(ApiService.class);
        Call<MarvelResultWrapper> getComicsCall = apiService.getComics(marvelApiRequestSignature.publicKey, this.limitComics, marvelApiRequestSignature.timeStamp, marvelApiRequestSignature.hashSignature);
        MarvelApi.executeRequest(getComicsCall, context, this);
    }

    @Override
    public void onRequestSuccess(int code, Response response) {
        ComicsController.this.marvelResultWrapper = (MarvelResultWrapper) response.body();
        this.loadDataListener.onSuccess(ComicsController.this.marvelResultWrapper.getData().getResults());
    }

    @Override
    public void onRequestFail(int code, String erroMessage) {
        this.loadDataListener.onRequestFail(erroMessage);
    }
}
