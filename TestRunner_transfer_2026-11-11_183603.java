package com.example.librarybookrecommnder;

public class TestRunner {
    public static void runTests() {
        // Test 1: MaxHeap
        MaxHeap heap = new MaxHeap();
        heap.insert(new Book(1, "Book A", "Author", "Genre", "Desc", 4.5));
        heap.insert(new Book(2, "Book B", "Author", "Genre", "Desc", 2.0));
        heap.insert(new Book(3, "Book C", "Author", "Genre", "Desc", 5.0));
        
        Book max = heap.extractMax();
        if (max != null && max.rating == 5.0) {
            System.out.println("TEST PASSED: MaxHeap extracted the highest rating.");
        } else {
            System.out.println("TEST FAILED: MaxHeap.");
        }

        // Test 2: HashTable
        HashTable ht = new HashTable(10);
        Book b1 = new Book(1, "Java Basics", "John", "Tech", "Desc", 4.0);
        ht.put("Java Basics", b1);
        if (ht.get("Java Basics") != null) {
            System.out.println("TEST PASSED: HashTable retrieved the book.");
        } else {
            System.out.println("TEST FAILED: HashTable.");
        }
    }
}