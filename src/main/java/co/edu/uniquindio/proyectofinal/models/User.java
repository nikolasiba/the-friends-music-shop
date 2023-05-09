package co.edu.uniquindio.proyectofinal.models;


import co.edu.uniquindio.proyectofinal.persistence.UsefullFile;

import java.io.IOException;
import java.util.ArrayList;

public class User {

    String userName;
    String password;
    String type;

    public User() {
    }

    public User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String singIn(String userName, String password) throws IOException {
        String type = "";
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> contenido = null;
        try {
            contenido = UsefullFile.leerArchivo("src/main/resources/persistence/Users.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linea = "";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            User userLogin = new User();
            userLogin.setUserName(linea.split("#")[0]);
            userLogin.setPassword(linea.split("#")[1]);
            userLogin.setType(linea.split("#")[2]);

            users.add(userLogin);
        }

        for (User user : users) {
            if (user.userName.equals(userName) && user.getPassword().equals(password)) {
                type = user.getType();
            }

        }

        return type;

    }
}
