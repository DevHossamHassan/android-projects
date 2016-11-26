package com.lets_go_to_perfection.todolistdemo0;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listTasks;
    ArrayAdapter adapter;
    List<String> data;
    private final static String TAG = "TodoListDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTasks = (ListView) findViewById(R.id.listNotes);
        data = new ArrayList<>();
        data.add("Note 1");
        data.add("Note 2");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listTasks.setAdapter(adapter);


    }

    private void addTask() {
        final EditText edtNewTask = new EditText(this);
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("Add Task")
                .setView(edtNewTask)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (edtNewTask.getText().toString().trim().length() > 0) {
                            data.add(edtNewTask.getText().toString());
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_add_task:
                Log.d(TAG, "add task");
                addTask();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
