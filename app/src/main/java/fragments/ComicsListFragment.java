package fragments;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ComicsListAdapter;
import controller.ComicsController;
import listeners.LoadDataListener;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListFragment extends Fragment implements LoadDataListener {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comic_list, container, false);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.comics_list_recycler_view);

        this.loadComicsList();

        return rootView;
    }

    private void loadComicsList() {
        ComicsController comicsController = new ComicsController();
        comicsController.getComicsList(this.getActivity().getBaseContext(), this);
    }

    @Override
    public void onSuccess(Object object) {
        try {
            ArrayList<Comic> comicsList = (ArrayList<Comic>) object;
            ComicsListAdapter comicsListAdapter = new ComicsListAdapter(this.getActivity().getApplicationContext(), comicsList);
            recyclerView.setAdapter(comicsListAdapter);
        } catch (ClassCastException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }

    @Override
    public void onRequestFail(String erroMessage) {
        Toast.makeText(this.getActivity().getBaseContext(), erroMessage, Toast.LENGTH_SHORT).show();
    }
}
