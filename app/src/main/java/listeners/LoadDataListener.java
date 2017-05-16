package listeners;

import retrofit2.Response;

/**
 * Created by fvillela on 5/15/17.
 */

public interface LoadDataListener {
    void onSuccess(Object object);
    void onRequestFail(String erroMessage);
}
