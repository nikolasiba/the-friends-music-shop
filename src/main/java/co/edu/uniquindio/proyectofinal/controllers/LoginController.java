package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button createUser;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnSingIn;

    @FXML
    private TextField txtPassword;

    @FXML
    void CreateUser(ActionEvent event) {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/createUser.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("create user");
            stage.setScene(scene);
            // Obtiene la escena actual
            Scene currentScene = ((Node) event.getSource()).getScene();
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void singIn(ActionEvent event) throws IOException {
        String test = "";
        Stage stage = new Stage();

        User userLogin = new User();
        if (txtUserName.getText().equals("") || txtPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);

            alert.setContentText("Los cmapos deben estar completos");

            alert.showAndWait();
        } else {
            test = userLogin.singIn(txtUserName.getText(), txtPassword.getText());
            if (test.equals("Admin")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admin_view.fxml"));

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
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (test.equals("user")) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/user_view.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());

                    UserController userController = fxmlLoader.getController();
                    userController.setUser(txtUserName.getText());

                    stage.setTitle("User");
                    stage.setScene(scene);
                    // Obtiene la escena actual
                    Scene currentScene = ((Node) event.getSource()).getScene();
                    // Obtiene el Stage actual
                    Stage currentStage = (Stage) currentScene.getWindow();
                    // Cierra el Stage actual

                    stage.show();
                    userController.loadFavorites(txtUserName.getText());
                    currentStage.close();
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);

                alert.setContentText("Usuario o contrasena incorrecta");

                alert.showAndWait();
            }


        }
    }


    @FXML
    void initialize() {
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'login_view.fxml'.";
        assert btnSingIn != null : "fx:id=\"btnSingIn\" was not injected: check your FXML file 'login_view.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'login_view.fxml'.";

    }
}