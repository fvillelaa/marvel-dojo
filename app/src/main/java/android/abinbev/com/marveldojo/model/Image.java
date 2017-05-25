package android.abinbev.com.marveldojo.model;

/**
 * Created by fvillela on 5/16/17.
 */

public class Image {
    String path;
    String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getImageUrl() {
        return path + "." + extension;
    }
}
