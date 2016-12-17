package com.lets_go_to_perfection.aast_todo.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lets_go_to_perfection.aast_todo.R;
import com.lets_go_to_perfection.aast_todo.models.Task;

import java.util.List;

/**
 * Created by Hossam on 12/10/2016.
 */

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, -1, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.view_row_list_view_main, parent, false);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtContent = (TextView) rowView.findViewById(R.id.firstLine);
            viewHolder.txtDate = (TextView) rowView.findViewById(R.id.secondLine);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(viewHolder);

        }
        ViewHolder holder = (ViewHolder) rowView.getTag();


        Task task = tasks.get(position);
        holder.txtContent.setText(task.getContent());
        holder.txtDate.setText(task.getCreatedAt());


        return rowView;
    }

    static class ViewHolder {
        public TextView txtContent;
        public TextView txtDate;
        public ImageView image;
    }
}
