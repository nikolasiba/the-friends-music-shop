package co.edu.uniquindio.proyectofinal.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateUserController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private URL location;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnCreateUser;

    @FXML
    private TextField txtPassword;


    @FXML
    void createUSer(ActionEvent event) throws IOException {

        User user = new User();

        boolean test = user.createUser(txtUserName.getText(), txtPassword.getText(), cbType.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        if (test) {
            alert.setContentText("todo correcto");
        } else {
            alert.setContentText("todo incorrecto");
        }
        alert.showAndWait();

    }

    @FXML
    void initialize() {
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'createUser.fxml'.";
        assert cbType != null : "fx:id=\"cbType\" was not injected: check your FXML file 'createUser.fxml'.";
        assert btnCreateUser != null : "fx:id=\"btnCreateUser\" was not injected: check your FXML file 'createUser.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'createUser.fxml'.";
        cbType.setItems(FXCollections.observableArrayList(
                "Admin", "user"));

    }
}
