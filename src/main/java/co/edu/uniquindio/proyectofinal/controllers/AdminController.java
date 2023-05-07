package co.edu.uniquindio.proyectofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnLoadImage;

    @FXML
    private TextField txtTypeArtist;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtNameSong;

    @FXML
    private TextField txtNameArtist;

    @FXML
    private TextField txtAlbum;

    @FXML
    private TextField txtNationality;

    @FXML
    private Button btnCreateSong;

    @FXML
    private TextField txtCodeArtist;

    @FXML
    private MenuButton dbtnSelectArtist;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtUrl;

    @FXML
    void createArtist(ActionEvent event) {

    }

    @FXML
    void selectArtist(ActionEvent event) {

    }

    @FXML
    void loadImage(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(btnLoadImage.getScene().getWindow());

        // Si el usuario ha seleccionado un archivo, guarda la imagen en tu proyecto
        if (selectedFile != null) {
            File destinationFile = new File("src/main/resources/images/" + selectedFile.getName()); // Cambia la ruta por la de tu proyecto
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Imagen subida correctamente.", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void initialize() {
        assert btnLoadImage != null : "fx:id=\"btnLoadImage\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtTypeArtist != null : "fx:id=\"txtTypeArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtDuration != null : "fx:id=\"txtDuration\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNameSong != null : "fx:id=\"txtNameSong\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNameArtist != null : "fx:id=\"txtNameArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtAlbum != null : "fx:id=\"txtAlbum\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNationality != null : "fx:id=\"txtNationality\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert btnCreateSong != null : "fx:id=\"btnCreateSong\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtCodeArtist != null : "fx:id=\"txtCodeArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert dbtnSelectArtist != null : "fx:id=\"dbtnSelectArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtGender != null : "fx:id=\"txtGender\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtUrl != null : "fx:id=\"txtUrl\" was not injected: check your FXML file 'admin_view.fxml'.";

    }

}