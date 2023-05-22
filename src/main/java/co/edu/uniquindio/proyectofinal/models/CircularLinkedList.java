package co.edu.uniquindio.proyectofinal.models;

import java.io.Serializable;

public class CircularLinkedList implements Serializable {

    private static final long serialVersionUID = 1L;
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public CircularLinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(Song song) {
        Node newNode = new Node(song);

        if (isEmpty()) {
            head = newNode;
            newNode.setNext(head);
        } else {
            Node current = head;
            while (current.getNext() != head) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setNext(head);
        }
    }

    public void delete(Song song) {
        if (isEmpty()) {
            return;
        }

        Node current = head;
        Node prev = null;

        while (current.getNext() != head) {
            if (current.getData().equals(song)) {
                break;
            }
            prev = current;
            current = current.getNext();
        }

        if (current == head) {
            head = head.getNext();
        }

        if (current.getData().equals(song)) {
            if (prev != null) {
                prev.setNext(current.getNext());
            }
            current.setNext(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("CircularLinkedList#");
        } else {
            Node current = head;
            do {
                sb.append(current.getData().toString()).append(",");
                current = current.getNext();
            } while (current != head);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("#");
        }
        return sb.toString();
    }

}