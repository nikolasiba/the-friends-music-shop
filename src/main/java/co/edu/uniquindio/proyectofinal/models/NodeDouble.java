package co.edu.uniquindio.proyectofinal.models;

import java.io.Serializable;

public class NodeDouble implements Serializable {
    private static final long serialVersionUID = 1L;

    Song song;
    NodeDouble last;
    NodeDouble next;

    public NodeDouble() {
    }

    public NodeDouble(Song song) {
        this.song = song;
        this.next = null;
        this.last = null;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public NodeDouble getLast() {
        return last;
    }

    public void setLast(NodeDouble last) {
        this.last = last;
    }

    public NodeDouble getNext() {
        return next;
    }

    public void setNext(NodeDouble next) {
        this.next = next;
    }


}
