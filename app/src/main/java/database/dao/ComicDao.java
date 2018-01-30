package database.dao;

import android.abinbev.com.marveldojo.model.Comic;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by fvillela on 29/01/18.
 */

@Dao
public interface ComicDao {
    @Query("SELECT * FROM comic WHERE id = :comicId")
    Comic get(String comicId);

    @Query("SELECT * FROM comic")
    List<Comic> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Comic> comics);

    @Delete
    void delete(Comic comic);
}
