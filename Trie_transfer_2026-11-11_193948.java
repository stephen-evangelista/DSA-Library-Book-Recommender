package com.example.librarybookrecommnder; 

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private class TrieNode {
        TrieNode[] children = new TrieNode[128]; 
        boolean isEndOfWord;
        List<Book> books = new ArrayList<>(); 
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String title, Book book) {
        TrieNode current = root;
        String lowerTitle = title.toLowerCase();
        for (char c : lowerTitle.toCharArray()) {
            int index = c;
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            if(!current.books.contains(book)) {
                current.books.add(book); 
            }
        }
        current.isEndOfWord = true;
    }

    public List<Book> searchPrefix(String prefix) {
        TrieNode current = root;
        String lowerPrefix = prefix.toLowerCase();
        
        for (char c : lowerPrefix.toCharArray()) {
            int index = c;
            if (current.children[index] == null) {
                return new ArrayList<>(); 
            }
            current = current.children[index];
        }
        return current.books; 
    }
}