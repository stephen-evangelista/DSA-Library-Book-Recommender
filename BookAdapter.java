package com.example.librarybookrecommnder; 

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() { return bookList.size(); }
    @Override
    public Object getItem(int position) { return bookList.get(position); }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book book = bookList.get(position);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvAuthor);
        TextView tvGenre = convertView.findViewById(R.id.tvGenre);
        TextView tvRating = convertView.findViewById(R.id.tvRating);

        tvTitle.setText(book.title);
        tvAuthor.setText("By: " + book.author);
        tvGenre.setText("Genre: " + book.genre);
        tvRating.setText("⭐ " + book.rating);

        return convertView;
    }
}
