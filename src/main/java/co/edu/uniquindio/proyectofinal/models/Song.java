package co.edu.uniquindio.proyectofinal.models;

import co.edu.uniquindio.proyectofinal.persistence.UsefullFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Song {

    String code;
    String name;
    String album;
    String image;
    String yeaar;
    String duration;
    String gender;
    String url;

    public Song() {
    }

    public Song(String code, String name, String album, String image, String yeaar, String duration, String gender, String url) {
        this.code = code;
        this.name = name;
        this.album = album;
        this.image = image;
        this.yeaar = yeaar;
        this.duration = duration;
        this.gender = gender;
        this.url = url;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYeaar() {
        return yeaar;
    }

    public void setYeaar(String yeaar) {
        this.yeaar = yeaar;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", image='" + image + '\'' +
                ", yeaar='" + yeaar + '\'' +
                ", duration='" + duration + '\'' +
                ", gender='" + gender + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public boolean createSong(String name, String album, String image, String year, String duration, String gender, String url) throws IOException {


        Date currentDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String code = formatDate.format(currentDate);

        if (!validateExistence(code)) {
            Song song = new Song();

            song.setCode(code);
            song.setName(name);
            song.setAlbum(album);
            song.setImage(image);
            song.setYeaar(year);
            song.setGender(gender);
            song.setDuration(duration);
            song.setUrl(url);

            String insert =
                    song.getCode() + "#" +
                    song.getName() + "#" +
                    song.getAlbum() + "#" +
                    song.getImage() + "#" +
                    song.getYeaar() + "#" +
                    song.getGender() + "#" +
                    song.getDuration() + "#" +
                    song.getUrl() + "\n";

            UsefullFile.guardarArchivo("src/main/resources/persistence/Songs.txt", insert, true);
            return true;

        }
        return false;


    }

    public boolean validateExistence(String code) {
        String type = "";
        HashMap<String, Author> authors = new HashMap<>();
        ArrayList<String> content = null;
        try {
            content = UsefullFile.leerArchivo("src/main/resources/persistence/authors.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linea = "";

        for (String s : content) {
            linea = s;
            Author author = new Author();
            author.setName(linea.split("#")[0]);
            author.setCode(linea.split("#")[1]);
            author.setType(linea.split("#")[3]);

            authors.put(author.getCode(), author);
        }

        return authors.containsKey(code);

    }
}
