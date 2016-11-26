package com.lets_go_to_perfection.todolistdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hossam on 11/19/2016.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    List<Task> tasksData;
    Context context;

    public TaskAdapter(Context context, List<Task> tasksData) {
        super(context, 0, tasksData);
        this.tasksData = tasksData;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ourView = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false);
        TextView textView = (TextView) ourView.findViewById(R.id.txtTask);
        textView.setText(tasksData.get(position).content);

        return ourView;
    }
}
