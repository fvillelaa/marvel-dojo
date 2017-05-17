package activities;

import android.abinbev.com.marveldojo.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import fragments.ComicsListFragment;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListActivity extends Activity {

    private ComicsListFragment comicsListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            comicsListFragment = new ComicsListFragment();
            getFragmentManager().beginTransaction().add(R.id.container, comicsListFragment).commit();
        }

        setContentView(R.layout.comics_list_activity);

        super.onCreate(savedInstanceState);
    }
}
