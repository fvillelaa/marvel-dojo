package activities;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import constants.Constants;
import database.RealmDatabaseManager;

public class ComicActivity extends AppCompatActivity {

    private ImageView imageViewComic;
    private TextView textViewID;
    private TextView textViewFormat;
    private TextView textViewPages;
    private TextView textViewTitle;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        this.setupViews();

        RealmDatabaseManager realmDatabaseManager = new RealmDatabaseManager(this);

        Intent intent = this.getIntent();
        String comicId = intent.getStringExtra(Constants.intentExtraComicId);


        Comic comic = (Comic) realmDatabaseManager.get(Comic.class, Comic.ID, comicId);
        Log.d("comicID", comic.getId());
        Log.d("title", comic.getTitle());

        if (comic.getImages().size() > 0) {
            Picasso.with(this).load(comic.getImages().get(0).getImageUrl()).into(imageViewComic);
        } else {
            Picasso.with(this).load(Constants.defaultImageUrl).into(imageViewComic);
        }

        textViewTitle.setText(comic.getTitle());
        textViewID.setText(comic.getId());
        textViewPages.setText("Total de páginas: " + comic.getPageCount());
    }

    private void setupViews() {
        this.imageViewComic = (ImageView) findViewById(R.id.imageView);
        this.textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        this.textViewID = (TextView) findViewById(R.id.textViewId);
        this.textViewPages = (TextView) findViewById(R.id.textViewPage);
    }
}
