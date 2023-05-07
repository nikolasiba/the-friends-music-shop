package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.models.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

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
    void singIn(ActionEvent event) throws IOException {
        String test = "";
        Stage stage = new Stage();

        UserLogin userLogin = new UserLogin();
        if (txtUserName.getText().equals("") && txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "los Campos no pueden estar vacios");
        } else {
            test = userLogin.singIn(txtUserName.getText(), txtPassword.getText());
            if (test.equals("admin")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admin_view.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //JOptionPane.showMessageDialog(null, "hola admin");
            } else if (test.equals("user")) {
                //JOptionPane.showMessageDialog(null, "hola user");

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