package co.edu.uniquindio.proyectofinal.models;

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
}
