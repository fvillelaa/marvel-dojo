package activities;

import android.abinbev.com.marveldojo.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import controller.ComicsController;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        super.onCreate(savedInstanceState);
    }
}
