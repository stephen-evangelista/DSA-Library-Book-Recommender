package com.example.librarybookrecommnder; 

public class Book {
    public int id;
    public String title;
    public String author;
    public String genre;
    public String description;
    public double rating;

    public Book(int id, String title, String author, String genre, String description, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
    }
}
