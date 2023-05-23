package co.edu.uniquindio.proyectofinal.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.models.Author;
import co.edu.uniquindio.proyectofinal.models.Song;
import co.edu.uniquindio.proyectofinal.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SongsArtistController {

    ObservableList<Song> songsList = FXCollections.observableArrayList();
    private String user;
    private String author;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Song> listviewSongs;

    @FXML
    void OnClickSongs(MouseEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/song_web_view.fxml"));
        Parent root = fxmlLoader.load();

        SongWebViewController songWebViewController = fxmlLoader.getController();
        songWebViewController.setPrimaryStage(stage);

        Scene scene = new Scene(root);
        stage.setTitle("Admin");
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {
            songWebViewController.stopPlayback();
        });

        stage.show();

        Song selectedSong = listviewSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            songWebViewController.playSong(selectedSong.getUrl());
        }
    }

    public void setCells() {
        Author authorModel = new Author();
        ArrayList<Song> arrayList = authorModel.getSongAuthor(author);
        songsList.setAll(arrayList);
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
                        hbox.setAlignment(Pos.CENTER_LEFT);

                        hbox.setPadding(new Insets(20, 20, 20, 20));

                        hbox.setSpacing(20);
                        //hbox.getChildren().addAll(imageView, new VBox(label1, label2, label3), favoriteButton);

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
                            label1.setText("Name: " + song.getName());
                            label2.setText("Gender: " + song.getGender());
                            label3.setText("Album: " + song.getAlbum());

                            // Agregar la imagen y los textos al HBox
                            hbox.getChildren().setAll(imageView, new VBox(label1, label2, label3));
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });

    }

    @FXML
    void initialize() {
        assert listviewSongs != null : "fx:id=\"listviewSongs\" was not injected: check your FXML file 'song_artist.fxml'.";


    }
}
