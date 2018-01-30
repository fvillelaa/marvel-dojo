package application;

import android.app.Application;

import database.AppDatabase;

/**
 * Created by fvillela on 29/01/18.
 */

public class MarvelApplication extends Application {

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = AppDatabase.buildAppDatabase(getApplicationContext());
    }

    public static AppDatabase getAppDatabase() {
        return database;
    }
}
