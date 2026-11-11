# DSA Library Book Recommender System

## 📌 Project Overview
This is an Android-based application developed as a final project for the Data Structures and Algorithms (DSA) course. The application serves as a library book recommender that allows users to quickly search for books and view top-rated recommendations. 

To demonstrate a deep understanding of DSA, the core logic of this app relies on custom-implemented Data Structures and Algorithms rather than relying on built-in database queries or library sorting functions.

## 🛠️ Tech Stack
*   **Language:** Java
*   **Platform:** Android (Built via CodeAssist mobile IDE)
*   **Data Source:** Local JSON File (`books.json` with 500+ records)

## 🧠 Data Structures Implemented
1.  **Array List:** Acts as the primary storage for all book records loaded from the JSON file.
2.  **Max-Heap (`MaxHeap.java`):** Used to efficiently extract the "Top 10 Recommended Books" based on the highest ratings.
    *   *Time Complexity:* \(O(\log n)\) for insertion and extraction.
3.  **Hash Table (`HashTable.java`):** Implements a custom hashing function with chaining (Linked Lists) for fast exact-match searching of book titles.
    *   *Time Complexity:* \(O(1)\) average time for lookup.
4.  **Trie (`Trie.java`):** A Prefix Tree used to provide fast autocomplete and prefix-matching for the search bar (e.g., typing "Har" instantly suggests "Harry Potter").
    *   *Time Complexity:* \(O(L)\) where \(L\) is the length of the search prefix.

## ⚙️ Algorithms Implemented
1.  **Merge Sort (`MergeSort.java`):** Used to sort the search results alphabetically by book title before displaying them to the user.
    *   *Time Complexity:* \(O(n \log n)\) on all cases.
2.  **Linear Search:** Used for partial string matching when a user types a query in the search bar (e.g., searching "Harry" matches "Harry Potter").

## 🚀 How to Run
1. Clone or download this repository.
2. Open the project in Android Studio or CodeAssist.
3. Build and install the APK on an Android device.
4. Upon launching, the app will read the `books.json` file from the `res/raw` folder and populate the custom data structures.

## 👨‍💻 Author
*   **Stephen** (Isulat mo full name mo dito)
*   Section: (Isulat mo section mo)
