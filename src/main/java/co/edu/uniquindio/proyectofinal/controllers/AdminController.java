package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.spi.AudioFileReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminController {

    ObservableList<String> items = FXCollections.observableArrayList();
    File selectedFile;
    @FXML
    private Button btnLogOut;

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
    private Button btnCreateAuthor;

    @FXML
    private Button btnCheckAuthor;


    @FXML
    private TextField txtCodeArtist;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtUrl;

    @FXML
    void logOut(ActionEvent event) {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login_view.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    void createAuthor(ActionEvent event) throws IOException {

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
                items.clear();

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
        } else if (cbSelectArtist.getValue() == null) {
            Alert alert = createAlert("El campo artista no puede estar vacio");
            alert.showAndWait();

        } else {
            boolean test = song.createSong(txtNameSong.getText(), txtAlbum.getText(), selectedFile.getName(), txtYear.getText(), txtDuration.getText(), txtGender.getText(), txtUrl.getText(), cbSelectArtist.getValue());
            Alert alert;
            if (test) {
                txtNameSong.setText("");
                txtAlbum.setText("");
                selectedFile = null;
                txtYear.setText("");
                txtDuration.setText("");
                txtGender.setText("");
                txtUrl.setText("");
                cbSelectArtist.getSelectionModel().clearSelection();
                cbSelectArtist.setPromptText("Seleccione un artista");

                alert = createAlert("todo correcto");
            } else {
                alert = createAlert("hubo un error ");
            }
            alert.showAndWait();
        }


    }

    @FXML
    void checkGender(ActionEvent event) {
        Author author = new Author();
        ArrayList<Song> songs = author.getAllSongs();

        Map<String, Integer> genreCountMap = new HashMap<>();


        for (Song song : songs) {
            String genre = song.getGender();


            if (genreCountMap.containsKey(genre)) {

                int count = genreCountMap.get(genre);
                genreCountMap.put(genre, count + 1);
            } else {

                genreCountMap.put(genre, 1);
            }
        }
        String genreWithMostSongs = "";
        int maxSongCount = 0;

        for (Map.Entry<String, Integer> entry : genreCountMap.entrySet()) {
            String genre = entry.getKey();
            int songCount = entry.getValue();

            if (songCount > maxSongCount) {
                maxSongCount = songCount;
                genreWithMostSongs = genre;
            }
        }

        // Imprimir el género con la mayor cantidad de canciones
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);

        alert.setContentText("El genero con mas canciones es: " + genreWithMostSongs);

        alert.showAndWait();

    }

    @FXML
    void checkAuthor(ActionEvent event) {
        User user = new User();
        Author author = new Author();

        ArrayList<Author> authors = author.getAuthors();
        ArrayList<User> users = user.getAllUser();

        Map<Author, Integer> reconocimientoArtistas = new HashMap<>();

        for (Author artista : authors) {
            int reconocimiento = 0;

            // Recorrer la lista circular doblemente enlazada de canciones del artista
            NodeDouble current = artista.getSongsList().getHeader();
            do {
                // Verificar si la canción es favorita para algún usuario
                for (User usuario : users) {
                    CircularLinkedList listaCircular = usuario.getCircularLinkedList();
                    if (listaCircular.getHead() != null) {
                        if (listaCircular.contains(current.getSong().getCode())) {
                            reconocimiento++;
                            // No es necesario seguir buscando en los usuarios
                        }
                    }
                }

                // Avanzar al siguiente nodo en la lista circular
                current = current.getNext();
            } while (current != null);

            // Almacenar el contador de reconocimiento del artista
            reconocimientoArtistas.put(artista, reconocimiento);
        }

        // Encontrar el artista más reconocido
        Author artistaMasReconocido = null;
        int maxReconocimiento = 0;
        for (Map.Entry<Author, Integer> entry : reconocimientoArtistas.entrySet()) {
            Author artista = entry.getKey();
            int reconocimiento = entry.getValue();
            if (reconocimiento > maxReconocimiento) {
                artistaMasReconocido = artista;
                maxReconocimiento = reconocimiento;
            }
        }

        // Imprimir el artista más reconocido
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        if (artistaMasReconocido != null) {
            alert.setContentText("Artista más reconocido: " + artistaMasReconocido.getName());
        } else {
            alert.setContentText("No se encontró un artista más reconocido.");
        }

        alert.showAndWait();
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
        if (array != null) {
            for (Author element : array) {

                items.add(element.getName());
            }
            cbSelectArtist.setItems(items);
        }


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
        assert btnCreateAuthor != null : "fx:id=\"btnCreateAuthor\" was not injected: check your FXML file 'admin_view.fxml'.";
        assert btnCheckAuthor != null : "fx:id=\"btnCheckAuthor\" was not injected: check your FXML file 'admin_view.fxml'.";


    }

}