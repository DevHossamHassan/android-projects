package com.lets_go_to_perfection.aast_todo.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lets_go_to_perfection.aast_todo.models.Task;
import com.lets_go_to_perfection.aast_todo.utils.UtilsDateTime;
import com.lets_go_to_perfection.aast_todo.utils.UtilsLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hossam on 12/10/2016.
 */

public class ToDoDatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "aast-todo-db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_TASKS = "tasks";

    // Tasks Table Columns
    private static final String KEY_TASK_ID = "id";
    private static final String KEY_TASK_CREATED_AT = "createdAt";
    private static final String KEY_TASK_CONTENT = "content";
    private static ToDoDatabaseHelper instance;

    public static synchronized ToDoDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (instance == null) {
            instance = new ToDoDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public ToDoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS +
                "(" +
                KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + // Define a primary key
                KEY_TASK_CREATED_AT + " DATETIME ," +
                KEY_TASK_CONTENT + " TEXT " +
                ")";

        db.execSQL(CREATE_TASKS_TABLE);
        UtilsLogger.d("The database is created for the FIRST time");
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
            onCreate(db);
            UtilsLogger.d("The database is updated from v: " + oldVersion + " to v: " + newVersion);
        }
    }

    // Insert a task into the database
    public void addTask(Task task) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_TASK_CREATED_AT, UtilsDateTime.get());
            values.put(KEY_TASK_CONTENT, task.getContent());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_TASKS, null, values);
            db.setTransactionSuccessful();
            UtilsLogger.d("Successful insertion for task: " + task.toString());
        } catch (Exception e) {
            UtilsLogger.e("Error while trying to add post to database", e);
        } finally {
            db.endTransaction();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        // SELECT * FROM TASKS
        String TASKS_SELECT_QUERY =
                String.format("SELECT * FROM %s ", TABLE_TASKS);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(TASKS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Task newTask = new Task();
                    int id = cursor.getInt(cursor.getColumnIndex(KEY_TASK_ID));
                    String createdAT = cursor.getString(cursor.getColumnIndex(KEY_TASK_CREATED_AT));
                    String content = cursor.getString(cursor.getColumnIndex(KEY_TASK_CONTENT));
                    newTask.setId(id);
                    newTask.setCreatedAt(createdAT);
                    newTask.setContent(content);

                    tasks.add(newTask);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            UtilsLogger.d("Error while trying to get posts from database", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tasks;
    }

    // Update task content
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_CONTENT, task.getContent());

        // Updating task content  for a task with that id
        return db.update(TABLE_TASKS, values, KEY_TASK_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    // Delete task
    public boolean deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID, task.getId());

        // Delete task for a task with that id
        return db.delete(TABLE_TASKS, KEY_TASK_ID + " = " + task.getId(), null) > 0;
    }

    public void deleteAllTasks() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_TASKS, null, null);
            db.setTransactionSuccessful();
            UtilsLogger.d("Successfully TABLE_TASKS deleted ");
        } catch (Exception e) {
            UtilsLogger.e("Error while trying to delete all posts and users", e);
        } finally {
            db.endTransaction();
        }
    }
}
