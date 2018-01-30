package fragments;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.MarvelResultWrapper;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import adapter.ComicsListAdapter;
import application.MarvelApplication;
import database.dao.ComicDao;
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private ComicDao comicDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_comic_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.comics_list_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        comicDao = MarvelApplication.getAppDatabase().comicDao();

        setupRecyclerView();

        setupSwipeRefresh();

        getHeroesFromApi();

        return rootView;
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHeroesFromApi();
            }
        });
    }

    private void setupRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void populateRecyclerView(List<Comic> comics) {
        comicsListAdapter = new ComicsListAdapter(getActivity(), comics);
        recyclerView.setAdapter(comicsListAdapter);

        comicsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        populateRecyclerView(comicDao.getAll());
    }

    private void getHeroesFromApi(){

        MarvelApiImpl.initMarvelApi();
        Retrofit retrofit = MarvelApiImpl.getRetrofit();
        MarvelApi marvelAPI = retrofit.create(MarvelApi.class);

        MarvelApiRequestSignature marvelApiRequestSignature = new MarvelApiRequestSignature();
        Call<MarvelResultWrapper> marvelResultWrapperCall =
                marvelAPI.listComics(marvelApiRequestSignature.publicKey,
                        marvelApiRequestSignature.timeStamp,
                        marvelApiRequestSignature.hashSignature,
                        20);

        MarvelApiImpl.executeRequest(marvelResultWrapperCall, new APIResponseListener() {
            @Override
            public void onSuccess(List<Comic> comics) {

                populateRecyclerView(comics);

                MarvelApplication.getAppDatabase().comicDao().saveAll(comics);

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(String s) {
                Log.d("ComicsList error", s);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setRefreshing(true);
    }
}
