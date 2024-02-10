package controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;
import util.PhotoDownloader;

public class GalleryController {
    private Gallery galleryModel;

    @FXML
    private TextField imageNameField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    public TextField searchTextField;


    @FXML
    public void initialize() {
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });

        imagesListView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (oldValue != null) imageNameField.textProperty().unbindBidirectional(oldValue.getNameProperty());
                    bindSelectedPhoto(newValue);
                })
        );
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
//        imageView.setImage(selectedPhoto.getPhotoData());
        imageView.imageProperty().bind(selectedPhoto.getPhotoProperty());
        imageNameField.textProperty().bindBidirectional(selectedPhoto.getNameProperty());
    }

    public void searchButtonClicked(ActionEvent event) {
        PhotoDownloader photoDownloader = new PhotoDownloader();
        galleryModel.clear();
        photoDownloader.searchForPhotos(searchTextField.getText()).subscribe(galleryModel::addPhoto);
    }
}

