package listeners;

import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;

import java.util.List;

/**
 * Created by fvillela on 5/23/17.
 */

public interface APIResponseListener {
    public void onSuccess(List<Comic> marvelResultWrapper);
    public void onError(String s);
}
