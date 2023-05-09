package co.edu.uniquindio.proyectofinal.models;

import co.edu.uniquindio.proyectofinal.persistence.UsefullFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Author implements Serializable {


    String code;
    String name;
    String nationality;
    String type;

    DoubleLinkedList songsList = new DoubleLinkedList();


    public Author() {
    }

    public Author(String code, String name, String nationality, String type) {
        this.code = code;
        this.name = name;
        this.nationality = nationality;
        this.type = type;
        this.songsList = null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DoubleLinkedList getSongsList() {
        return songsList;
    }

    public void setSongsList(DoubleLinkedList songsList) {
        this.songsList = songsList;
    }


    @Override
    public String toString() {
        return "Author{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", nationality='" + nationality + '\'' + ", type='" + type + '\'' + ", songsList=" + songsList + '}';
    }

    public boolean crateAuthor(String name, String code, String type, String nationality) {

        try {
            if (!validateExistence(name)) {
                Author author = new Author();

                author.setName(name);
                author.setCode(code);
                author.setNationality(nationality);
                author.setType(type);

                // cargar la lista actual de autores desde el archivo serializado
                ArrayList<Author> authors = (ArrayList<Author>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/authors.txt");

                // agregar el nuevo autor a la lista
                if (authors != null) {
                    authors.add(author);
                    try {
                        UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/authors.txt", authors);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    ArrayList<Author> authors1 = new ArrayList<>();
                    authors1.add(author);
                    try {
                        UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/authors.txt", authors1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean validateExistence(String name) throws IOException, ClassNotFoundException {
        String type = "";
        HashMap<String, Author> authors = new HashMap<>();
        ArrayList<Author> content = null;

        content = (ArrayList<Author>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/authors.txt");
        if (content != null) {
            for (Author author : content) {
                authors.put(author.getName(), author);
            }
        }else {
            return false;
        }


        return authors.containsKey(name);

    }

    public ArrayList<Author> getAuthors() {
        ArrayList<Author> authors = new ArrayList<Author>();

        try {
            authors = (ArrayList<Author>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/authors.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return authors;


    }
}
