package com.example.librarybookrecommnder; 

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private List<Book> allBooks;
    private HashTable hashTable;
    private MaxHeap maxHeap;
    private Trie bookTrie; 

    public DataManager(Context context) {
        allBooks = new ArrayList<>();
        hashTable = new HashTable(2000);
        maxHeap = new MaxHeap();
        bookTrie = new Trie(); 
        
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
                    obj.getInt("id"), obj.getString("title"), obj.getString("author"), 
                    obj.getString("genre"), obj.getString("description"), obj.getDouble("rating")
                );
                
                allBooks.add(book);
                hashTable.put(book.title, book);
                maxHeap.insert(book);
                bookTrie.insert(book.title, book); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getRecommendations(int limit) {
        List<Book> recommended = new ArrayList<>();
        MaxHeap tempHeap = new MaxHeap();
        for(Book b : allBooks) { tempHeap.insert(b); }
        
        for (int i = 0; i < limit; i++) {
            if (!tempHeap.isEmpty()) { recommended.add(tempHeap.extractMax()); }
        }
        return recommended;
    }

    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        
        List<Book> trieResults = bookTrie.searchPrefix(query);
        for(Book b : trieResults) {
            results.add(b);
        }

        for (Book book : allBooks) {
            if (book.author.toLowerCase().contains(query.toLowerCase())) {
                if (!results.contains(book)) {
                    results.add(book);
                }
            }
        }

        return MergeSort.sortByTitle(results);
    }
}
