package model;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import javafx.beans.property.*;
import javafx.scene.image.Image;


public class Photo {

    private ObjectProperty<String> name;

    private final ObjectProperty<Image> photoData;

    public Photo(String extension, byte[] photoData) {
        this.photoData = new SimpleObjectProperty<>(new Image(new ByteArrayInputStream(photoData)));
        this.name = new SimpleObjectProperty<>(UUID.randomUUID().toString() + "." + extension);
    }

    public String getName() {
        return name.getValue();
    }

    public ObjectProperty<String> getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public Image getPhotoData() {
        return photoData.getValue();
    }

    public ObjectProperty<Image> getPhotoProperty() {
        return photoData;
    }
}
