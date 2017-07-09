package activities;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import constants.Constants;
import database.RealmDatabaseManager;

public class ComicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        RealmDatabaseManager realmDatabaseManager = new RealmDatabaseManager(this);

        Intent intent = this.getIntent();
        String comicId = intent.getStringExtra(Constants.intentExtraComicId);


        Comic comic = (Comic) realmDatabaseManager.get(Comic.class, Comic.ID, comicId);
        Log.d("comicID", comic.getId());
        Log.d("title", comic.getTitle());
        Log.d("URL", comic.getImages().get(0).getImageUrl());
    }
}
