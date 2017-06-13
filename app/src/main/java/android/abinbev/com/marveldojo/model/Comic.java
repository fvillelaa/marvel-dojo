package android.abinbev.com.marveldojo.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by fvillela on 5/15/17.
 */

public class Comic extends RealmObject {

    String title;
    RealmList<Image> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(RealmList<Image> images) {
        this.images = images;
    }

}
