package database;

import android.abinbev.com.marveldojo.model.Comic;
import android.abinbev.com.marveldojo.model.Image;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import database.dao.ComicDao;

/**
 * Created by fvillela on 29/01/18.
 */

@Database(entities = {Comic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ComicDao comicDao();

    public static AppDatabase buildAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "marvel-database")
                .allowMainThreadQueries() //just for testing
                .build();
    }
}
