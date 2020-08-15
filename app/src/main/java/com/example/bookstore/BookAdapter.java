package com.example.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class BookAdapter extends ArrayAdapter<Book>
{
    public BookAdapter(Context context, ArrayList<Book> books)
    {
        super(context,0,books);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list,
                    parent, false);
        }

        Book cBook = getItem(position);

        ImageView img=(ImageView) listItemView.findViewById(R.id.img);
        TextView title = (TextView)listItemView.findViewById(R.id.title);
        TextView subtitle = (TextView) listItemView.findViewById(R.id.subtitle);
        TextView price = (TextView) listItemView.findViewById(R.id.price);



        title.setText(cBook.getTitle());
        subtitle.setText(cBook.getSubtitle());
        price.setText(cBook.getPrice());

        Picasso.get().load(cBook.getImage()).into(img);

        return listItemView;
    }

}
