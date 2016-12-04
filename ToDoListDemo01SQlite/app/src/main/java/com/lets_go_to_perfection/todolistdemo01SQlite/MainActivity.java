package com.lets_go_to_perfection.todolistdemo01SQlite;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {
    private final static String DATA_KEY = "data_key";
    ListView listTasks;
    ArrayAdapter adapter;
    ArrayList<String> data;
    private final static String TAG = "TodoListDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            data = getData();
            if (data == null || data.isEmpty()) {
                data = new ArrayList<>();
            }

        } else {
            data = savedInstanceState.getStringArrayList(DATA_KEY);
        }

        listTasks = (ListView) findViewById(R.id.listNotes);
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
                            saveData(edtNewTask.getText().toString());
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(DATA_KEY, data);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void saveData(String newTask) {
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("todo-db",
                MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES(?," +
                "" + System.currentTimeMillis() + ");",new String[]{newTask});
        sqLiteDatabase.close();
        /*
        use the "?" binding form to Prevent SQLite database injection.
        use "?" as placeholder in your sql command and
        pass the value as an element inside a string []
        then pass this string array in the second parameter in execSQl() or rowQuery() methods

        note : there was a method called  sqlEscapeString()
        This method is deprecated because they want to encourage everyone
        to use the "?" binding form.
        */
    }

    private ArrayList<String> getData() {
        data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("todo-db",
                MODE_PRIVATE, null);
        Log.d(TAG, "todo created  ");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tasks(content TEXT," +
                " created_at INTEGER);");
        Log.d(TAG, "tasks created  ");

//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('ay 7aga '," +
//                "" + System.currentTimeMillis() + ");");
//        Log.d(TAG,"ay 7aga  inserted  ");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 1'," +
//                "" + System.currentTimeMillis() + ");");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 2 '," +
//                "" + System.currentTimeMillis() + ");");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 3 '," +
//                "" + System.currentTimeMillis() + ");");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 4 '," +
//                "" + System.currentTimeMillis() + ");");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 5 '," +
//                "" + System.currentTimeMillis() + ");");
//        sqLiteDatabase.execSQL("INSERT INTO tasks VALUES('task 6 '," +
//                "" + System.currentTimeMillis() + ");");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tasks", null);
        if (cursor.moveToFirst()) {
            Log.d(TAG, "cursor contains data ");
            do {
                String content = cursor.getString(0);
                int createdAt = cursor.getInt(1);
                data.add(content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return data;
    }

}