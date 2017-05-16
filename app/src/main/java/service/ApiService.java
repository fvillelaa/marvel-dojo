package service;

import android.abinbev.com.marveldojo.model.MarvelResultWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fvillela on 5/15/17.
 */

public interface ApiService {
    @GET("/v1/public/comics")
    Call<MarvelResultWrapper> getComics(@Query("apikey") String key, @Query("limit") int limit, @Query("ts") long timestamp, @Query("hash") String hash);
}
