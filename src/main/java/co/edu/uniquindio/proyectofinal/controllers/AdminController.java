package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.models.Author;
import co.edu.uniquindio.proyectofinal.models.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.sound.sampled.spi.AudioFileReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class AdminController {

    ObservableList<String> items = FXCollections.observableArrayList();
    File selectedFile;

    @FXML
    private Button btnLoadImage;

    @FXML
    private TextField txtTypeArtist;

    @FXML
    private TextField txtYear;

    @FXML
    private ComboBox<String> cbSelectArtist;

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
    private TextField txtGender;

    @FXML
    private TextField txtUrl;

    @FXML
    void createArtist(ActionEvent event) throws IOException {

        if (txtNameArtist.getText().equals("")) {

            Alert alert = createAlert("el campo nombre no puede estar vacio .");
            alert.showAndWait();

        } else if (txtCodeArtist.getText().equals("")) {
            Alert alert = createAlert("el campo codigo no puede estar vacio .");
            alert.showAndWait();

        } else if (txtNationality.getText().equals("")) {
            Alert alert = createAlert("el campo nacionalidad no puede estar vacio .");
            alert.showAndWait();

        } else if (txtTypeArtist.getText().equals("")) {
            Alert alert = createAlert("el campo tipo no puede estar vacio .");
            alert.showAndWait();

        } else {
            Author author = new Author();
            boolean test = author.crateAuthor(txtNameArtist.getText(), txtCodeArtist.getText(), txtTypeArtist.getText(), txtNationality.getText());

            if (test) {
                ArrayList<Author> array = author.getAuthors();
                for (Author element : array) {
                    items.add(element.getName());
                }
                cbSelectArtist.setItems(items);

                txtNameArtist.setText("");
                txtCodeArtist.setText("");
                txtNationality.setText("");
                txtTypeArtist.setText("");

                Alert alert = createAlert("el autor se anadio correctamente.");
                alert.showAndWait();

            } else {
                Alert alert = createAlert("el autor no se pudo anadir. El nombre ya existe o hubo un error.");
                alert.showAndWait();
            }
        }


    }

    @FXML
    void createSong(ActionEvent event) throws IOException {
        Song song = new Song();

        if (txtNameSong.getText().equals("")) {
            Alert alert = createAlert("El campo nombre no puede estar vacio");
            alert.showAndWait();
        } else if (txtAlbum.getText().equals("")) {
            Alert alert = createAlert("El campo album no puede estar vacio");
            alert.showAndWait();

        } else if (selectedFile == null) {
            Alert alert = createAlert("El campo caratula no puede estar vacio");
            alert.showAndWait();

        } else if (txtYear.getText().equals("")) {
            Alert alert = createAlert("El campo album no puede estar vacio");
            alert.showAndWait();

        } else if (txtDuration.getText().equals("")) {
            Alert alert = createAlert("El campo album no puede estar vacio");
            alert.showAndWait();

        } else if (txtGender.getText().equals("")) {
            Alert alert = createAlert("El campo genero no puede estar vacio");
            alert.showAndWait();

        } else if (txtUrl.getText().equals("")) {
            Alert alert = createAlert("El campo album no puede estar vacio");
            alert.showAndWait();

        }else {
            boolean test = song.createSong(txtNameSong.getText(), txtAlbum.getText() , selectedFile.getName() , txtYear.getText(), txtDuration.getText(),txtGender.getText(), txtUrl.getText()  );
            Alert alert;
            if (test){
                alert = createAlert("todo correcto");
            }else {
                alert = createAlert("hubo un error ");
            }
            alert.showAndWait();
        }


    }

    @FXML
    void loadImage(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));
        selectedFile = fileChooser.showOpenDialog(btnLoadImage.getScene().getWindow());

        // Si el usuario ha seleccionado un archivo, guarda la imagen en tu proyecto
        if (selectedFile != null) {
            File destinationFile = new File("src/main/resources/images/" + selectedFile.getName()); // Cambia la ruta por la de tu proyecto
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Imagen cargada correctamente.", ButtonType.OK);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("La imagen se ha subido correctamente.");
            alert.showAndWait();
        }

    }

    public Alert createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    @FXML
    void initialize() {
        Author author = new Author();

        ArrayList<Author> array = author.getAuthors();


        for (Author element : array) {

            items.add(element.getName());
        }
        cbSelectArtist.setItems(items);

        assert btnLoadImage != null : "fx:id=\"btnLoadImage\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtTypeArtist != null : "fx:id=\"txtTypeArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert cbSelectArtist != null : "fx:id=\"cbSelectArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtDuration != null : "fx:id=\"txtDuration\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNameSong != null : "fx:id=\"txtNameSong\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNameArtist != null : "fx:id=\"txtNameArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtAlbum != null : "fx:id=\"txtAlbum\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtNationality != null : "fx:id=\"txtNationality\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert btnCreateSong != null : "fx:id=\"btnCreateSong\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtCodeArtist != null : "fx:id=\"txtCodeArtist\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtGender != null : "fx:id=\"txtGender\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert txtUrl != null : "fx:id=\"txtUrl\" was not injected: check your FXML file 'admin_view.fxml'.";

    }

}