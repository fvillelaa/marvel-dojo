package database;

import android.abinbev.com.marveldojo.model.Image;
import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by fvillela on 29/01/18.
 */

public class ImageTypeConverter {

    @TypeConverter
    public static List<Image> stringToImagesList(String images) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {}.getType();
        return gson.fromJson(images, type);
    }

    @TypeConverter
    public static String imagesListToString(List<Image> images) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {}.getType();
        return gson.toJson(images, type);
    }
}
