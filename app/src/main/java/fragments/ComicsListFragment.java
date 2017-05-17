package fragments;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Retrofit;
import service.MarvelAPI;
import service.MarvelApiImpl;
import service.MarvelApiRequestSignature;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListFragment extends Fragment{

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       MarvelApiImpl.initMarvelApi();
        Retrofit retrofit = MarvelApiImpl.getRetrofit();
        MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);

        MarvelApiRequestSignature marvelApiRequestSignature = new MarvelApiRequestSignature();
        Call<MarvelResultWrapper> marvelResultWrapperCall = marvelAPI.listComics(marvelApiRequestSignature.publicKey, marvelApiRequestSignature.timeStamp, marvelApiRequestSignature.hashSignature, 10);
        MarvelApiImpl.executeRequest(marvelResultWrapperCall);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comic_list, container, false);

        return rootView;
    }

}
