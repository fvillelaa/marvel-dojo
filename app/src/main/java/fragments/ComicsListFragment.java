package fragments;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.ComicsListAdapter;
import listeners.APIResponseListener;
import retrofit2.Call;
import retrofit2.Retrofit;
import service.MarvelAPI;
import service.MarvelApiImpl;
import service.MarvelApiRequestSignature;

import static android.abinbev.com.marveldojo.R.id.container;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListFragment extends Fragment{

    private RecyclerView recyclerView;
    private ComicsListAdapter comicsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_comic_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.comics_list_recycler_view);

        setupRecyclerView();

        getHeroes();

        return rootView;
    }

    private void setupRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void getHeroes(){

        MarvelApiImpl.initMarvelApi();
        Retrofit retrofit = MarvelApiImpl.getRetrofit();
        MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);

        MarvelApiRequestSignature marvelApiRequestSignature = new MarvelApiRequestSignature();
        Call<MarvelResultWrapper> marvelResultWrapperCall =
                marvelAPI.listComics(marvelApiRequestSignature.publicKey,
                        marvelApiRequestSignature.timeStamp,
                        marvelApiRequestSignature.hashSignature,
                        10);

        MarvelApiImpl.executeRequest(marvelResultWrapperCall, new APIResponseListener() {
            @Override
            public void onSuccess(List<Comic> comics) {

                comicsListAdapter = new ComicsListAdapter(getActivity(), comics);
                recyclerView.setAdapter(comicsListAdapter);

                comicsListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(String s) {
                Log.d("ComicsList error", s);
            }
        });

    }
}
