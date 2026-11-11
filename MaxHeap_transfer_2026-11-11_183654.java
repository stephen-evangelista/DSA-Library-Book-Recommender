package com.example.librarybookrecommnder; 

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private List<Book> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    public void insert(Book book) {
        heap.add(book);
        int currentIndex = heap.size() - 1;
        
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (heap.get(currentIndex).rating > heap.get(parentIndex).rating) {
                Book temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public Book extractMax() {
        if (heap.isEmpty()) return null;
        
        Book max = heap.get(0);
        Book last = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapify(0);
        }
        return max;
    }

    private void heapify(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && heap.get(left).rating > heap.get(largest).rating) {
            largest = left;
        }
        if (right < heap.size() && heap.get(right).rating > heap.get(largest).rating) {
            largest = right;
        }

        if (largest != index) {
            Book temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);
            heapify(largest);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public int size() {
        return heap.size();
    }
}