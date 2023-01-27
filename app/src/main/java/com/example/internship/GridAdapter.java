package com.example.internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> url;
    ArrayList<String> title;
    ArrayList<String> id;

    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<String> url, ArrayList<String> title,ArrayList<String> id) {
        this.context = context;
        this.url = url;
        this.title = title;
        this.id=id;
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.grid_item,null);

        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);

        Picasso.get().load(url.get(position)).into(imageView);

        textView.setText(title.get(position));

        return convertView;
    }
}