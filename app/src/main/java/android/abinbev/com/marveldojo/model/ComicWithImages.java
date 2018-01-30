package android.abinbev.com.marveldojo.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by fvillela on 31/01/18.
 */

public class ComicWithImages {

    @Embedded
    private Comic comic;

    @Relation(parentColumn = "id", entityColumn = "comicId", entity = Image.class)
    private List<Image> images;

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
