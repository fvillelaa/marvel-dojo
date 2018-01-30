package activities;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import application.MarvelApplication;
import constants.Constants;
import database.dao.ComicDao;

public class ComicActivity extends Activity {

    private ImageView imageViewComic;
    private TextView textViewID;
    private TextView textViewPages;
    private TextView textViewTitle;
    private Button buttonDelete;

    private ComicDao comicDao;

    public ComicActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        comicDao = MarvelApplication.getAppDatabase().comicDao();

        this.setupViews();

        Intent intent = this.getIntent();
        final String comicId = intent.getStringExtra(Constants.intentExtraComicId);

        final Comic comic = comicDao.get(comicId);

        if (comic.getImages().size() > 0) {
            Picasso.with(this).load(comic.getImages().get(0).getImageUrl()).into(imageViewComic);
        } else {
            Picasso.with(this).load(Constants.defaultImageUrl).into(imageViewComic);
        }

        textViewTitle.setText(comic.getTitle());
        textViewID.setText(comic.getId());
        textViewPages.setText(String.format(Locale.getDefault(), "Total de páginas: %d", comic.getPageCount()));

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comicDao.delete(comic);
                finish();
            }
        });
    }

    private void setupViews() {
        this.imageViewComic = (ImageView) findViewById(R.id.imageView);
        this.textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        this.textViewID = (TextView) findViewById(R.id.textViewId);
        this.textViewPages = (TextView) findViewById(R.id.textViewPage);
        this.buttonDelete = (Button) findViewById(R.id.buttonDelete);
    }
}
