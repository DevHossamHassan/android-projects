package com.lets_go_to_perfection.todolistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> tasksData;
    ListView listTask;
    EditText edtAddNewTask;
    Button btnAddTask;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init views
        init();
        //add some dummy data
        createDummyData();

        taskAdapter = new TaskAdapter(this, tasksData);
        listTask.setAdapter(taskAdapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtAddNewTask.getText().toString().trim().length() > 0) {
                    Task task = new Task(edtAddNewTask.getText().toString());
                    tasksData.add(task);
                    taskAdapter.notifyDataSetChanged();
                    edtAddNewTask.setText("");

                }
            }
        });

        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void createDummyData() {
        tasksData = new ArrayList<>();
        tasksData.add(new Task("Task " + 0));
        tasksData.add(new Task("Task " + 1));
    }

    private void init() {
        listTask = (ListView) findViewById(R.id.listTasks);
        edtAddNewTask = (EditText) findViewById(R.id.edtNewTask);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
    }

}
