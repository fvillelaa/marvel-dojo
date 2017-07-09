package fragments;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import adapter.ComicsListAdapter;
import database.RealmDatabaseManager;
import io.realm.Realm;
import listeners.APIResponseListener;
import retrofit2.Call;
import retrofit2.Retrofit;
import service.MarvelApi;
import service.MarvelApiImpl;
import service.MarvelApiRequestSignature;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListFragment extends Fragment{

    private RecyclerView recyclerView;
    private ComicsListAdapter comicsListAdapter;
    private RealmDatabaseManager realmDatabaseManager;

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
        MarvelApi marvelAPI = retrofit.create(MarvelApi.class);

        MarvelApiRequestSignature marvelApiRequestSignature = new MarvelApiRequestSignature();
        Call<MarvelResultWrapper> marvelResultWrapperCall =
                marvelAPI.listComics(marvelApiRequestSignature.publicKey,
                        marvelApiRequestSignature.timeStamp,
                        marvelApiRequestSignature.hashSignature,
                        10);

        MarvelApiImpl.executeRequest(marvelResultWrapperCall, new APIResponseListener() {
            @Override
            public void onSuccess(List<Comic> comics) {

                List comicList = comics;

                comicsListAdapter = new ComicsListAdapter(getActivity(), comics);
                recyclerView.setAdapter(comicsListAdapter);

                comicsListAdapter.notifyDataSetChanged();

                realmDatabaseManager = new RealmDatabaseManager(getActivity());

                realmDatabaseManager.saveOrUpdateAll(comicList, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Deu certo, que show", Toast.LENGTH_SHORT).show();
                        List comicsFromDatabase = realmDatabaseManager.getAll(Comic.class);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getActivity(), "ERROR::: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(String s) {
                Log.d("ComicsList error", s);
            }
        });

    }
}
