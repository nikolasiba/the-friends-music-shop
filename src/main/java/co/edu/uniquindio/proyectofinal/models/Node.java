package co.edu.uniquindio.proyectofinal.models;

import java.io.Serializable;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private Song data;
    private Node next;

    public Node(Song data) {
        this.data = data;
        this.next = null;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}