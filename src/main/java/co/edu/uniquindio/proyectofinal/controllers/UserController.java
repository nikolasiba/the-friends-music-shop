package co.edu.uniquindio.proyectofinal.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import co.edu.uniquindio.proyectofinal.models.Author;
import co.edu.uniquindio.proyectofinal.models.Song;
import co.edu.uniquindio.proyectofinal.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UserController {

    private String user;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }


    ObservableList<Song> favoritesList = FXCollections.observableArrayList();
    ObservableList<Song> songsList = FXCollections.observableArrayList();
    ObservableList<Author> authorsList = FXCollections.observableArrayList();


    @FXML
    private ListView<Song> listviewSongs;

    @FXML
    private ListView<Song> listViewFavorites;

    @FXML
    private ListView<Author> listViewAuthors;
    @FXML
    private Button btnLogOut;


    @FXML
    void onClickArtist(MouseEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/song_artist.fxml"));
        Parent root = fxmlLoader.load();

        SongsArtistController songsArtistController = fxmlLoader.getController();

        Author author = listViewAuthors.getSelectionModel().getSelectedItem();
        String test = getUser();
        songsArtistController.setUser(test);
        songsArtistController.setAuthor(author.getName());
        songsArtistController.setCells();
        Scene scene = new Scene(root);
        stage.setTitle("Canciones");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login_view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Admin");
        stage.setScene(scene);
        // Obtiene la escena actual
        Scene currentScene = ((Node) event.getSource()).getScene();
        // Obtiene el Stage actual
        Stage currentStage = (Stage) currentScene.getWindow();
        // Cierra el Stage actual
        currentStage.close();
        stage.show();

    }

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
    @FXML
    void OnClickFavorite(MouseEvent event) throws IOException {
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

        Song selectedSong = listViewFavorites.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            songWebViewController.playSong(selectedSong.getUrl());
        }
    }
    public void loadFavorites(String currentUser) throws IOException {
        User user = new User();
        ArrayList<Song> arrayList = new ArrayList<>();
        arrayList = user.loadFavorites(currentUser);

        favoritesList.setAll(arrayList);
        listViewFavorites.setItems(favoritesList);

        listViewFavorites.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
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

                    final Button favoriteButton = new Button();

                    {
                        favoriteButton.setOnAction(actionEvent -> {
                            User user = new User();

                            try {
                                boolean test = user.saveFavorite(getUser(), getItem());
                                if (test){
                                    ArrayList<Song> arrayList = user.loadFavorites(getUser());
                                    favoritesList.setAll(arrayList);
                                    listViewFavorites.setItems(favoritesList);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText(null);

                                    alert.setContentText("Se anadio a favoritos");

                                    alert.showAndWait();
                                }else{
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText(null);

                                    alert.setContentText("no se anadio a favoritos ya existe");

                                    alert.showAndWait();
                                }

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });

                        favoriteButton.setPrefWidth(130);
                        favoriteButton.setPrefHeight(30);

                        favoriteButton.setText("add to favorites");
                    }

                    final HBox hbox = new HBox(imageView, new VBox(label1, label2, label3), favoriteButton);

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
                            hbox.getChildren().setAll(imageView, new VBox(label1, label2, label3), favoriteButton);
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
                            label1.setText("Name: " + author.getName());
                            label2.setText("type: " + author.getType());
                            label3.setText("Code: " + author.getCode());

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
