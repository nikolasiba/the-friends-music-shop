package co.edu.uniquindio.proyectofinal.models;

import co.edu.uniquindio.proyectofinal.persistence.UsefullFile;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    String code;
    String name;
    String album;
    String image;
    String year;
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
        this.year = yeaar;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        return "Song{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", album='" + album + '\'' + ", image='" + image + '\'' + ", year='" + year + '\'' + ", duration='" + duration + '\'' + ", gender='" + gender + '\'' + ", url='" + url + '\'' + '}';
    }

    public Song createSongFromString(String songString) {
        String[] songAttributes = songString.split("#");
        if (songAttributes.length < 8) {
            return null; // No se pueden crear correctamente los atributos de la canción
        }

        String code = songAttributes[0];
        String name = songAttributes[1];
        String album = songAttributes[2];
        String image = songAttributes[3];
        String year = songAttributes[4];
        String duration = songAttributes[5];
        String gender = songAttributes[6];
        String url = songAttributes[7];

        return new Song(code, name, album, image, year, duration, gender, url);
    }


    public boolean createSong(String name, String album, String image, String year, String duration, String gender, String url, String author) {

        ArrayList<Author> authors = new ArrayList<>();
        HashMap<String, Author> content = new HashMap<>();
        try {
            authors = (ArrayList<Author>) UsefullFile.cargarRecursoSerializado("src/main/resources/persistence/Authors.txt");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (authors != null) {
            for (Author author1 : authors) {
                content.put(author1.getName(), author1);
            }
            authors.clear();
        }


        for (Map.Entry<String, Author> entry : content.entrySet()) {

            String key = entry.getKey();
            Author currentAuthor = entry.getValue();
            if (currentAuthor.getName().equals(author)) {
                Date currentDate = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
                String code = formatDate.format(currentDate);
                Song song = new Song();
                song.setCode(code);
                song.setName(name);
                song.setAlbum(album);
                song.setImage(image);
                song.setYear(year);
                song.setGender(gender);
                song.setDuration(duration);
                song.setUrl(url);
                currentAuthor.getSongsList().addSong(song);

            }
        }
        try {

            for (Map.Entry<String, Author> entry : content.entrySet()) {

                String key = entry.getKey();
                Author currentAuthor = entry.getValue();
                authors.add(currentAuthor);

            }
            UsefullFile.salvarRecursoSerializado("src/main/resources/persistence/Authors.txt", authors);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
