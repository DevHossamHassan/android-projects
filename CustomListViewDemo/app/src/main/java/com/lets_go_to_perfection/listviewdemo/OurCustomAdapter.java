package com.lets_go_to_perfection.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hossam on 11/12/2016.
 */

class OurCustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> data;

    OurCustomAdapter(Context context, ArrayList<String> data) {
        super(context, -1, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.view_row_list_view_main, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(data.get(position));

        //change the icon for android Windows and iPhone
        String s = data.get(position);
        s = s.toLowerCase();
        if (s.startsWith("android")) {
            imageView.setImageResource(R.drawable.ic_android);
        } else if (s.startsWith("iphone")) {
            imageView.setImageResource(R.drawable.ic_iphone);
        } else if (s.startsWith("windows")) {
            imageView.setImageResource(R.drawable.ic_windows);
        }

        return rowView;
    }
}
