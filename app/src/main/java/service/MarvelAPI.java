package service;

import android.abinbev.com.marveldojo.model.MarvelResultWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fvillela on 5/16/17.
 */

public interface MarvelAPI {

    @GET("/v1/public/comics")
    Call<MarvelResultWrapper> listComics(@Query("apikey") String apikey, @Query("ts") long ts, @Query("hash") String hash, @Query("limit") int limit); // users/myuser@ciandt.com/repos


}
