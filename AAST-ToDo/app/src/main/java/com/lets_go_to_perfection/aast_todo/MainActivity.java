package com.lets_go_to_perfection.aast_todo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lets_go_to_perfection.aast_todo.adpaters.TaskAdapter;
import com.lets_go_to_perfection.aast_todo.databases.ToDoDatabaseHelper;
import com.lets_go_to_perfection.aast_todo.models.Task;
import com.lets_go_to_perfection.aast_todo.utils.UtilsDateTime;
import com.lets_go_to_perfection.aast_todo.utils.UtilsLogger;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Task> tasks;
    TaskAdapter adapter;

    ListView listViewTasks;
    ToDoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // In any activity just pass the context and use the singleton method
        dbHelper = ToDoDatabaseHelper.getInstance(getApplicationContext());
        tasks = new ArrayList<Task>();
        listViewTasks = (ListView) findViewById(R.id.listViewMain);
        tasks = dbHelper.getAllTasks();
        UtilsLogger.d(tasks.toString());
        adapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(adapter);


        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Task task = tasks.get(position);
                Toast.makeText(MainActivity.this, task.toString(), Toast.LENGTH_LONG).show();
            }
        });
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
                            adapter.notifyDataSetChanged();
                            Task task = new Task();
                            task.setContent(edtNewTask.getText().toString());
                            task.setCreatedAt(UtilsDateTime.get());
                            tasks.add(task);
                            dbHelper.addTask(task);
                            adapter.notifyDataSetChanged();
                            UtilsLogger.d("Successfully added new task"+task.toString());

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
                addTask();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
