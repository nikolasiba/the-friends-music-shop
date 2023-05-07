package co.edu.uniquindio.proyectofinal.models;

public class Autor {

    String code;
    String nombre;
    String nationality;
    String type;

    DoubleLinkedList songsList = new DoubleLinkedList();

    public Autor() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Autor{" +
                "code='" + code + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nationality='" + nationality + '\'' +
                ", type='" + type + '\'' +
                ", songsList=" + songsList +
                '}';
    }
}
