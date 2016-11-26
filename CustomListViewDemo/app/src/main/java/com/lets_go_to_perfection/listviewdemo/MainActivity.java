package com.lets_go_to_perfection.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list;
    ListView listview;
    OurCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listViewMain);

        for (int i = 0; i < 50; i++) {
            list.add("Android");
            list.add("Iphone");
            list.add("Windows");
        }

        adapter = new OurCustomAdapter(this, list);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                String itemData = list.get(position);
                Toast.makeText(MainActivity.this, itemData, Toast.LENGTH_LONG).show();
            }
        });
    }
}






