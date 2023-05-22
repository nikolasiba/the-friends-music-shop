package co.edu.uniquindio.proyectofinal.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class SongWebViewController {


    public void setPrimaryStage(Stage primaryStage) {
    }

    public void stopPlayback() {
        webViewSong.getEngine().load(null); // Detiene la reproducción
    }

    public void playSong(String url) {
        webViewSong.getEngine().load(url);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webViewSong;



    @FXML
    void initialize() {
        assert webViewSong != null : "fx:id=\"webViewSong\" was not injected: check your FXML file 'song_web_view.fxml'.";



    }
}