package co.edu.uniquindio.proyectofinal.models;

import java.io.Serializable;

public class DoubleLinkedList implements Serializable {
    private static final long serialVersionUID = 1L;

    NodeDouble header;


    public NodeDouble getHeader() {
        return header;
    }

    public void setHeader(NodeDouble header) {
        this.header = header;
    }

    public void addSong(Song song) {
        NodeDouble newNode = new NodeDouble(song);

        if (header == null) {
            header = newNode;
        } else {
            NodeDouble lastNode = header;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.setNext(newNode);
            newNode.setLast(lastNode);
        }
    }

    public void delete(Song song) {
        NodeDouble currentNode = header;

        while (currentNode != null) {
            if (currentNode.getSong().equals(song)) {
                if (currentNode.getLast() != null) {
                    currentNode.getLast().setNext(currentNode.getNext());
                } else {
                    header = currentNode.getNext();
                }
                if (currentNode.getNext() != null) {
                    currentNode.getNext().setLast(currentNode.getLast());
                }
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    public NodeDouble search(String titulo) {
        NodeDouble currentNode = header;

        while (currentNode != null) {
            if (currentNode.getSong().getName().equals(titulo)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }


}
