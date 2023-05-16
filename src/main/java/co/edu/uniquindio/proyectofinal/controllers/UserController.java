package co.edu.uniquindio.proyectofinal.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.models.Author;
import co.edu.uniquindio.proyectofinal.models.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class UserController {

    ObservableList<Song> authorsList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Song> listviewSongs;

    @FXML
    private ListView<Song> listViewFavorites;

    @FXML
    private ListView<Song> listViewAuthors;


    @FXML
    void initialize() {
        assert listviewSongs != null : "fx:id=\"listviewSongs\" was not injected: check your FXML file 'user_view.fxml'.";
        assert listViewFavorites != null : "fx:id=\"listViewFavorites\" was not injected: check your FXML file 'user_view.fxml'.";
        assert listViewAuthors != null : "fx:id=\"listViewAuthors\" was not injected: check your FXML file 'user_view.fxml'.";
        Author author = new Author();

        ArrayList<Song> test = author.getAllSongs();
        authorsList.addAll(test);


        listviewSongs.setItems(authorsList);

        listviewSongs.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> listView) {
                return new ListCell<Song>() {
                    final ImageView imageView = new ImageView();
                    final Label label1 = new Label();
                    final Label label2 = new Label();
                    final Label label3 = new Label();
                    final HBox hbox = new HBox(imageView, new VBox(label1, label2, label3));

                    @Override
                    public void updateItem(Song song, boolean empty) {
                        super.updateItem(song, empty);
                        if (empty || song == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Configurar la imagen
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/images/" + song.getImage())).toString());
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            imageView.setImage(image);

                            // Configurar los textos
                            label1.setText(song.getName());
                            label2.setText(song.getGender());
                            label3.setText(song.getAlbum());

                            // Agregar la imagen y los textos al HBox
                            hbox.getChildren().setAll(imageView, new VBox(label1, label2, label3));
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });

    }


}
