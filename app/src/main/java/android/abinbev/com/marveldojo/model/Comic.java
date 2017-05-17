package android.abinbev.com.marveldojo.model;

import java.util.List;

/**
 * Created by fvillela on 5/15/17.
 */

public class Comic {

    String title;
    List<Image> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
