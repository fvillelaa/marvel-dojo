package android.abinbev.com.marveldojo.model;

import java.util.List;

/**
 * Created by fvillela on 5/15/17.
 */

public class Comic {
    private String id;
    private String title;
    private List<Image> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
