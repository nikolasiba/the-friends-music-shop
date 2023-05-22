package co.edu.uniquindio.proyectofinal.models;


import co.edu.uniquindio.proyectofinal.persistence.UsefullFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    String userName;
    String password;
    String type;

    CircularLinkedList circularLinkedList =  new CircularLinkedList();

    public User() {
    }

    public User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.circularLinkedList = null;
    }

    public CircularLinkedList getCircularLinkedList() {
        return circularLinkedList;
    }

    public void setCircularLinkedList(CircularLinkedList circularLinkedList) {
        this.circularLinkedList = circularLinkedList;
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
            users = (ArrayList<User>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/Users.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (users != null) {
            for (User user : users) {
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    type = user.getType();
                }
            }
        }

        return type;

    }

    public void saveFavorite(String currentUser, Song song) throws IOException {
        String type = "";
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> contenido = null;
        try {
            users = (ArrayList<User>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/Users.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (users != null) {
            for (User user : users) {
                if (user.getUserName().equals(currentUser)) {
                    user.getCircularLinkedList().insert(song);
                }
            }
        }

        UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/Users.txt", users);


    }


    public ArrayList<Song> loadFavorites(String currentUser) throws IOException {
        String type = "";
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Song> favorites = new ArrayList<>();
        try {
            users = (ArrayList<User>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/Users.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (users != null) {
            for (User user : users) {
                if (user.getUserName().equals(currentUser)) {

                    if (user.getCircularLinkedList().isEmpty()) {

                        return favorites;
                    }

                    Node current = user.getCircularLinkedList().getHead();
                    do {
                        favorites.add(current.getData());
                        current = current.getNext();
                    } while (current != user.getCircularLinkedList().getHead());

                }
            }
        }

        UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/Users.txt", users);


        return favorites;
    }

    public boolean createUser(String userName, String password, String type) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            users = (ArrayList<User>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/Users.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (users == null) {
            users = new ArrayList<>();
            User user = new User();

            user.setUserName(userName);
            user.setPassword(password);
            user.setType(type);
            users.add(user);

        } else {
            for (User user : users) {
                if (user.getUserName().equals(userName)) {

                    return false;

                }
            }
            User user = new User();

            user.setUserName(userName);
            user.setPassword(password);
            user.setType("123");
            users.add(user);
        }


        UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/Users.txt", users);


        return true;
    }


}
