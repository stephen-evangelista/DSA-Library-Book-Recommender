package com.example.librarybookrecommnder; 

public class HashTable {
    private class Node {
        String key; 
        Book value; 
        Node next;

        Node(String key, Book value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, Book value) {
        int index = getHash(key);
        Node head = buckets[index];

        Node current = head;
        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }

    public Book get(String key) {
        int index = getHash(key);
        Node current = buckets[index];

        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                return current.value; 
            }
            current = current.next;
        }
        return null; 
    }
}
