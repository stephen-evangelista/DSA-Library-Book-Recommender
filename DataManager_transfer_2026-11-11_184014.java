package com.example.librarybookrecommnder; // <-- Palitan mo kung iba package name mo

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    // 1st Data Structure: Array List (Main storage)
    private List<Book> allBooks;
    
    // 2nd Data Structure: Hash Table (For exact search)
    private HashTable hashTable;
    
    // 3rd Data Structure: Max Heap (For recommendations)
    private MaxHeap maxHeap;

    public DataManager(Context context) {
        allBooks = new ArrayList<>();
        hashTable = new HashTable(2000); // Capacity na 2000 para iwas collision
        maxHeap = new MaxHeap();
        
        loadDataFromJSON(context);
    }

    private void loadDataFromJSON(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(
                context.getResources().getIdentifier("books", "raw", context.getPackageName())
            );
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String jsonString = scanner.hasNext() ? scanner.next() : "";
            
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Book book = new Book(
                    obj.getInt("id"), 
                    obj.getString("title"), 
                    obj.getString("author"), 
                    obj.getString("genre"), 
                    obj.getString("description"), 
                    obj.getDouble("rating")
                );
                
                // I-save sa Array List
                allBooks.add(book);
                
                // I-save sa Hash Table (Key: Title, Value: Book)
                hashTable.put(book.title, book);
                
                // I-save sa Max Heap (Para pag-extract natin, top rated agad)
                maxHeap.insert(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Algorithm 1: Use Max Heap to get top recommendations
    public List<Book> getRecommendations(int limit) {
        List<Book> recommended = new ArrayList<>();
        // Kailangan nating mag-extract pero hindi natin dapat sirain yung buong heap.
        // Kaya gagawa tayo ng temporary heap para sa extraction.
        MaxHeap tempHeap = new MaxHeap();
        for(Book b : allBooks) {
            tempHeap.insert(b);
        }
        
        for (int i = 0; i < limit; i++) {
            if (!tempHeap.isEmpty()) {
                recommended.add(tempHeap.extractMax());
            }
        }
        return recommended;
    }

    // Algorithm 2: Linear Search + Merge Sort
    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        
        // Step 1: Try exact match using Hash Table (Fast O(1))
        Book exactMatch = hashTable.get(query);
        if (exactMatch != null) {
            results.add(exactMatch);
        }

        // Step 2: Partial search using Array List (Linear Search)
        for (Book book : allBooks) {
            if (book.title.toLowerCase().contains(query.toLowerCase()) || 
                book.author.toLowerCase().contains(query.toLowerCase())) {
                // Iwasan ma-doble yung exact match na nahanap na natin
                if (exactMatch == null || book.id != exactMatch.id) {
                    results.add(book);
                }
            }
        }

        // Step 3: I-sort yung results alphabetically gamit ang custom Merge Sort natin!
        return MergeSort.sortByTitle(results);
    }
}