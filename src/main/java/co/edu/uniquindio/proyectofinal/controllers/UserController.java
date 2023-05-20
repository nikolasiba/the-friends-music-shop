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
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class UserController {

    ObservableList<Song> songsList = FXCollections.observableArrayList();
    ObservableList<Author> authorsList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Song> listviewSongs;

    @FXML
    private ListView<Song> listViewFavorites;

    @FXML
    private ListView<Author> listViewAuthors;

    @FXML
    void OnClickSongs(MouseEvent event) {
        Song song = new Song();
        song = songsList.get(0);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Imagen cargada correctamente.", ButtonType.OK);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(song.toString());
        alert.showAndWait();


    }


    @FXML
    void initialize() {
        assert listviewSongs != null : "fx:id=\"listviewSongs\" was not injected: check your FXML file 'user_view.fxml'.";
        assert listViewFavorites != null : "fx:id=\"listViewFavorites\" was not injected: check your FXML file 'user_view.fxml'.";
        assert listViewAuthors != null : "fx:id=\"listViewAuthors\" was not injected: check your FXML file 'user_view.fxml'.";
        Author author = new Author();
        ArrayList<Author> authors = author.getAuthors();
        ArrayList<Song> test = author.getAllSongs();
        authorsList.addAll(authors);
        songsList.addAll(test);

        listViewAuthors.setItems(authorsList);
        listviewSongs.setItems(songsList);

        listviewSongs.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> listView) {
                return new ListCell<Song>() {
                    final ImageView imageView = new ImageView();
                    final Label label1 = new Label();
                    final Label label2 = new Label();
                    final Label label3 = new Label();
                    final HBox hbox = new HBox(imageView, new VBox(label1, label2, label3));
                    {
                        hbox.setPadding(new Insets(20, 20, 20, 20));

                        hbox.setSpacing(20);
                    }

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

        listViewAuthors.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
            @Override
            public ListCell<Author> call(ListView<Author> listView) {
                return new ListCell<Author>() {
                    final ImageView imageView = new ImageView();
                    final Label label1 = new Label();
                    final Label label2 = new Label();
                    final Label label3 = new Label();
                    final HBox hbox = new HBox(imageView, new VBox(label1, label2, label3));

                    {
                        hbox.setPadding(new Insets(20, 20, 20, 20));

                        hbox.setSpacing(10);
                    }


                    @Override
                    public void updateItem(Author author, boolean empty) {
                        super.updateItem(author, empty);
                        if (empty || author == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Configurar la imagen
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/images/user.jpg")).toString());
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            imageView.setImage(image);

                            // Configurar los textos
                            label1.setText(author.getName());
                            label2.setText(author.getType());
                            label3.setText(author.getCode());

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
