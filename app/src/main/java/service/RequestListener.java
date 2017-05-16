package service;

import retrofit2.Response;

/**
 * Created by fvillela on 5/15/17.
 */

public interface RequestListener {

    void onRequestSuccess(int code, Response response);
    void onRequestFail(int code, String erroMessage);
}
