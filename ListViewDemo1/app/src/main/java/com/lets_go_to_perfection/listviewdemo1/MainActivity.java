package com.lets_go_to_perfection.listviewdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMain;
    ArrayAdapter adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Iphone");
            list.add("Windows");
            list.add("Android");
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listViewMain = (ListView) findViewById(R.id.listViewMain);
        listViewMain.setAdapter(adapter);

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item number " + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
