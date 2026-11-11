package com.example.librarybookrecommnder; // <-- Palitan mo kung iba package name mo

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    // Algorithm: Merge Sort for sorting List of Books by Title
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public static List<Book> sortByTitle(List<Book> books) {
        if (books.size() <= 1) {
            return books;
        }

        int mid = books.size() / 2;
        List<Book> left = new ArrayList<>(books.subList(0, mid));
        List<Book> right = new ArrayList<>(books.subList(mid, books.size()));

        left = sortByTitle(left);
        right = sortByTitle(right);

        return merge(left, right);
    }

    private static List<Book> merge(List<Book> left, List<Book> right) {
        List<Book> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            // Compare titles alphabetically (case-insensitive)
            if (left.get(i).title.compareToIgnoreCase(right.get(j).title) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }
}